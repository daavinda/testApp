package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import market.common.orm.model.Item;
import market.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void saveSale(Long buyerId, String itemName, BigDecimal unitPrice, BigDecimal quantity) {

        BuyerItem buyerItem = new BuyerItem();

        Buyer buyer = buyerService.getBuyerById(buyerId);
        Item item = itemService.findByName(itemName);

        buyerItem.setBuyer(buyer);
        buyerItem.setItem(item);
        buyerItem.setDate(new Date());
        buyerItem.setType(Item.ItemType.NORMAL);
        buyerItem.setQuantity(quantity);
        buyerItem.setBuyerUnitPrice(unitPrice);
        buyerItem.setStatus(BuyerItem.Status.ACTIVE);

        BigDecimal amount = quantity.multiply(unitPrice);
        pendingPaymentService.updateWithSelling(buyer, amount);

        buyerItemService.saveBuyerItem(buyerItem);
    }

    @Override
    public void removeSale(Long item) {

        BuyerItem buyerItem = buyerItemService.findById(item);

        BigDecimal amount = buyerItem.getBuyerUnitPrice().multiply(buyerItem.getQuantity());
        pendingPaymentService.updateWithDeleteSelling(buyerItem.getBuyer(), amount);

        buyerItem.setStatus(BuyerItem.Status.REMOVED);
        buyerItemService.saveBuyerItem(buyerItem);

    }
}
