package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import market.common.orm.model.Item;
import market.common.orm.model.SystemUser;
import market.common.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class SellingServiceImpl implements SellingService {

    private final static Logger logger = LoggerFactory.getLogger(SellingServiceImpl.class);

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

        logger.info("Save Sale Request Received" + " - " + buyerId + " - " + itemName + " - " + unitPrice + " - " + quantity + " - " + saleType);

        BuyerItem buyerItem = new BuyerItem();

        Buyer buyer = buyerService.getBuyerById(buyerId);
        Item item = itemService.findByName(itemName);
        SystemUser currentUser = systemUserService.getCurrentUser();
        BigDecimal amount;
        try {
            amount = quantity.multiply(unitPrice);
        } catch (Exception e) {
            amount = BigDecimal.ZERO;
        }

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
        buyerItemService.saveBuyerItem(buyerItem);
        pendingPaymentService.updateWithSelling(buyer, amount);
        itemService.updateWithSelling(item, quantity, saleType);
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
