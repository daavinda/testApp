package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import market.common.orm.model.CRStatus;
import market.common.orm.repo.BuyerItemRepository;
import market.common.service.BuyerItemService;
import market.common.service.BuyerService;
import market.common.service.CRStatusService;
import market.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by devinda on 12/1/18.
 */
@Service
public class BuyerItemServiceImpl implements BuyerItemService {

    @Autowired
    private BuyerItemRepository buyerItemRepository;
    @Autowired
    private CRStatusService crStatusService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ItemService itemService;

    @Override
    public List<BuyerItem> getAllBuyerItems() {
        return buyerItemRepository.findAll();
    }

    @Override
    public List<BuyerItem> findByStatus(String status) {
        return buyerItemRepository.findByStatus(status);
    }

    @Override
    public List<BuyerItem> findByDateAndStatus(Date date) {
        return buyerItemRepository.findByDateAndStatus(date, BuyerItem.Status.ACTIVE);
    }

    @Override
    public List<BuyerItem> findByDateAndStatusAndBuyer(Date date, Long buyerId) {
        return buyerItemRepository.findByDateAndStatusAndBuyer(date, BuyerItem.Status.ACTIVE, buyerService.getBuyerById(buyerId));
    }

    @Override
    public List<BuyerItem> findByStatusAndBuyerAndDateBetween(Date from, Date to, Long buyerId) {
        return buyerItemRepository.findByStatusAndBuyerAndDateBetween(BuyerItem.Status.ACTIVE, buyerService.getBuyerById(buyerId), from, to);
    }


    @Override
    public List<BuyerItem> findByStatusAndType(String status, String type) {
        return buyerItemRepository.findByStatusAndType(status, type);
    }

    @Override
    public void saveBuyerItem(BuyerItem buyerItem) {
        crStatusService.updateStatus();
        buyerItemRepository.saveAndFlush(buyerItem);
    }

    @Override
    public BuyerItem findById(Long item) {
        return buyerItemRepository.findOne(item);
    }

    @Override
    public void removeItem(BuyerItem item) {
        buyerItemRepository.delete(item);
    }

    @Override
    public List<BuyerItem> findByStatusAndDateBetween(BuyerItem.Status status, Date from, Date to) {
        return buyerItemRepository.findByStatusAndDateBetween(status, from, to);
    }

    @Override
    public List<BuyerItem> findByStatusAndDateAndItem(BuyerItem.Status status, Date date, Long itemId) {
        return buyerItemRepository.findByStatusAndDateAndItem(status, date, itemService.findById(itemId));
    }
}
