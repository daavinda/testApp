package market.common.service;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;

import java.util.List;

public interface BuyerItemService {

    List<BuyerItem> getAllBuyerItems();

    List<BuyerItem> findByStatus(String status);

    void saveBuyerItem(BuyerItem buyerItem);

    BuyerItem findById(Long item);

    void removeItem(BuyerItem item);
}
