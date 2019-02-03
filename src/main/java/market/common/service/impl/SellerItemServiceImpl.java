package market.common.service.impl;

import market.common.orm.model.SellerItem;
import market.common.orm.repo.SellerItemRepository;
import market.common.service.CRStatusService;
import market.common.service.SellerItemService;
import market.common.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellerItemServiceImpl implements SellerItemService {

    @Autowired
    private SellerItemRepository sellerItemRepository;
    @Autowired
    private CRStatusService crStatusService;
    @Autowired
    private SellerService sellerService;

    @Override
    public List<SellerItem> getAllSellerItems() {
        return sellerItemRepository.findAll();
    }

    @Override
    public List<SellerItem> findByStatus(String status) {
        return sellerItemRepository.findByStatus(status);
    }

    @Override
    public List<SellerItem> findByDateAndStatus(Date date) {
        return sellerItemRepository.findByDateAndStatus(date, SellerItem.Status.ACTIVE);
    }

    @Override
    public List<SellerItem> findByDateAndStatusAndSeller(Date date, Long sellerId) {
        return sellerItemRepository.findByDateAndStatusAndSeller(date, SellerItem.Status.ACTIVE, sellerService.findById(sellerId));
    }

    @Override
    public List<SellerItem> findByStatusAndSellerAndDateBetween(Date from, Date to, Long sellerId) {
        return sellerItemRepository.findByStatusAndSellerAndDateBetween(SellerItem.Status.ACTIVE, sellerService.findById(sellerId), from, to);
    }

    @Override
    public List<SellerItem> findByStatusAndType(String status, String type) {
        return sellerItemRepository.findByStatusAndType(status, type);
    }

    @Override
    public void saveSellerItem(SellerItem sellerItem) {
        crStatusService.updateStatus();
        sellerItemRepository.saveAndFlush(sellerItem);
    }

    @Override
    public SellerItem findById(Long item) {
        return sellerItemRepository.findOne(item);
    }

    @Override
    public void removeItem(SellerItem item) {
        sellerItemRepository.delete(item);
    }

    @Override
    public List<SellerItem> findByStatusAndDateBetween(SellerItem.Status status, Date from, Date to) {
        return sellerItemRepository.findByStatusAndDateBetween(status, from, to);
    }
}
