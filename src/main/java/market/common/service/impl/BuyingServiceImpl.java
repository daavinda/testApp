package market.common.service.impl;

import market.common.orm.model.*;
import market.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BuyingServiceImpl implements BuyingService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerItemService sellerItemService;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public void saveBuying(Long sellerId, String itemName, BigDecimal unitPrice, BigDecimal quantity) {

        SellerItem sellerItem = new SellerItem();

        Seller seller = sellerService.findById(sellerId);
        Item item = itemService.findByName(itemName);
        SystemUser currentUser = systemUserService.getCurrentUser();
        BigDecimal amount = quantity.multiply(unitPrice);

        sellerItem.setSeller(seller);
        sellerItem.setItem(item);
        sellerItem.setDate(new Date());
        sellerItem.setQuantity(quantity);
        sellerItem.setSellerUnitPrice(unitPrice);
        sellerItem.setStatus(SellerItem.Status.ACTIVE);
        sellerItem.setAddedUser(currentUser);
        sellerItem.setAmount(amount);

        sellerItemService.saveSellerItem(sellerItem);
        pendingPaymentService.updateWithBuying(seller, amount);
        itemService.updateWithBuying(item, quantity);
    }

    @Override
    public void removeBuying(Long item) {

        SellerItem sellerItem = sellerItemService.findById(item);
        itemService.updateWithSelling(sellerItem.getItem(), sellerItem.getQuantity(), null);

        BigDecimal amount = sellerItem.getSellerUnitPrice().multiply(sellerItem.getQuantity());
        pendingPaymentService.updateWithDeleteBuying(sellerItem.getSeller(), amount);
        SystemUser currentUser = systemUserService.getCurrentUser();

        sellerItem.setStatus(SellerItem.Status.REMOVED);
        sellerItem.setRemovedUser(currentUser);
        sellerItemService.saveSellerItem(sellerItem);

    }
}
