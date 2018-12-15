package market.common.service.impl;

import market.common.orm.model.SellerItem;
import market.common.orm.repo.SellerItemRepository;
import market.common.service.SellerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerItemServiceImpl implements SellerItemService {

    @Autowired
    private SellerItemRepository sellerItemRepository;

    @Override
    public List<SellerItem> getAllSellerItems() {
        return sellerItemRepository.findAll();
    }

    @Override
    public List<SellerItem> findByStatus(String status) {
        return sellerItemRepository.findByStatus(status);
    }

    @Override
    public List<SellerItem> findByStatusAndType(String status, String type) {
        return sellerItemRepository.findByStatusAndType(status, type);
    }

    @Override
    public void saveSellerItem(SellerItem sellerItem) {
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
}
