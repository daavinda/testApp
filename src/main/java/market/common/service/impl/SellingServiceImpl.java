package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import market.common.orm.model.Item;
import market.common.service.BuyerItemService;
import market.common.service.BuyerService;
import market.common.service.ItemService;
import market.common.service.SellingService;
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

        buyerItemService.saveBuyerItem(buyerItem);


    }
}
