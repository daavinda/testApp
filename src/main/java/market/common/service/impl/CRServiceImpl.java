package market.common.service.impl;

import market.common.orm.model.CR;
import market.common.orm.repo.CRRepository;
import market.common.service.CRService;
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
        cr.setFromDate(new Date());
        crRepository.saveAndFlush(cr);
    }

    @Override
    public void remove(CR cr) {
        crRepository.delete(cr);
    }
}
