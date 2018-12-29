package market.common.service.impl;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.CRStatus;
import market.common.orm.repo.BuyerItemRepository;
import market.common.service.BuyerItemService;
import market.common.service.CRStatusService;
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
}
