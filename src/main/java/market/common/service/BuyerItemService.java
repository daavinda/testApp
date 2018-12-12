package market.common.service;

import market.common.orm.model.BuyerItem;

import java.util.List;

public interface BuyerItemService {

    List<BuyerItem> getAllBuyerItems();

    void saveBuyerItem(BuyerItem buyerItem);
}
