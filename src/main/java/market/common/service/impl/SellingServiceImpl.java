package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import market.common.orm.model.Item;
import market.common.orm.model.SystemUser;
import market.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SellingServiceImpl implements SellingService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private BuyerItemService buyerItemService;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public void saveSale(Long buyerId, String itemName, BigDecimal unitPrice, BigDecimal quantity, Long saleType) {

        BuyerItem buyerItem = new BuyerItem();

        Buyer buyer = buyerService.getBuyerById(buyerId);
        Item item = itemService.findByName(itemName);
        itemService.updateWithSelling(item, quantity, saleType);
        SystemUser currentUser = systemUserService.getCurrentUser();
        BigDecimal amount = quantity.multiply(unitPrice);

        buyerItem.setBuyer(buyer);
        buyerItem.setItem(item);
        buyerItem.setDate(new Date());
        buyerItem.setQuantity(quantity);
        buyerItem.setBuyerUnitPrice(unitPrice);
        buyerItem.setStatus(BuyerItem.Status.ACTIVE);
        buyerItem.setAddedUser(currentUser);
        buyerItem.setAmount(amount);
        if (saleType == 2) {
            buyerItem.setSaleType(saleType);
        }
        pendingPaymentService.updateWithSelling(buyer, amount);

        buyerItemService.saveBuyerItem(buyerItem);
    }

    @Override
    public void removeSale(Long item) {

        BuyerItem buyerItem = buyerItemService.findById(item);
        if (buyerItem.getSaleType() != null && buyerItem.getSaleType() == 2) {
            itemService.updateWithRemoveSale(buyerItem);
        } else {
            itemService.updateWithBuying(buyerItem.getItem(), buyerItem.getQuantity());
        }

        BigDecimal amount = buyerItem.getBuyerUnitPrice().multiply(buyerItem.getQuantity());
        pendingPaymentService.updateWithDeleteSelling(buyerItem.getBuyer(), amount);

        SystemUser currentUser = systemUserService.getCurrentUser();

        buyerItem.setStatus(BuyerItem.Status.REMOVED);
        buyerItem.setRemovedUser(currentUser);
        buyerItemService.saveBuyerItem(buyerItem);

    }
}
