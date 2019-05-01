package market.common.service.impl;

import market.common.orm.model.CR;
import market.common.orm.model.Item;
import market.common.orm.repo.CRRepository;
import market.common.service.CRService;
import market.common.service.CRStatusService;
import market.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CRServiceImpl implements CRService {

    @Autowired
    private CRRepository crRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CRStatusService crStatusService;


    @Override
    public List<CR> findAll() {
        return crRepository.findAll();
    }

    @Override
    public void save(Long itemId, BigDecimal unitPrice, BigDecimal quantity) {
        CR cr = new CR();
        cr.setQuantity(quantity);
        cr.setUnitPrice(unitPrice);
        cr.setItem(itemService.findById(itemId));
        cr.setDate(new Date());
        cr.setStatus(CR.Status.ACTIVE);
        cr.setAmount(unitPrice.multiply(quantity));
        crRepository.saveAndFlush(cr);
    }

    @Override
    public void remove(CR cr) {
        crRepository.delete(cr);
        crStatusService.updateStatus();
    }

    @Override
    public CR findById(Long id) {
        return crRepository.findOne(id);
    }

    @Override
    public List<CR> findByDate(Date date) {
        return crRepository.findByDate(date);
    }

    @Override
    public List<CR> findByDateBetween(Date from, Date to) {
        return crRepository.findByDateBetween(from, to);
    }

    @Override
    public void addToFreezer(Long crId) {
        CR cr = findById(crId);
        Item item = cr.getItem();
        if (item.getCrFreezerQty() != null && item.getCrFreezerQty().compareTo(new BigDecimal(0)) > 0) {
            item.setCrFreezerQty(item.getCrFreezerQty().add(cr.getQuantity()));
        } else {
            item.setCrFreezerQty(cr.getQuantity());
        }
        item.setQuantity(new BigDecimal(0));
        item.setUnitPrice(cr.getUnitPrice());
        itemService.saveItem(item);
        remove(cr);
    }

    @Override
    public void finishCr() {

        List<Item> allItems = itemService.findByType(Item.ItemType.NORMAL);
        for (Item item : allItems) {
            item.setQuantity(BigDecimal.ZERO);
            itemService.saveItem(item);
        }

        List<CR> crList = findByDate(new Date());
        for (CR cr : crList) {
//            if(cr.getStatus()==CR.Status.ACTIVE) {
            Item item = cr.getItem();
            item.setQuantity(cr.getQuantity());
            item.setUnitPrice(cr.getUnitPrice());
            itemService.saveItem(item);
            cr.setStatus(CR.Status.INACTIVE);
            crRepository.saveAndFlush(cr);
//            }
        }
        crStatusService.finishCr();
    }
}
