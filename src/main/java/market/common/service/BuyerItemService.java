package market.common.service;

import market.common.orm.model.BuyerItem;

import java.util.Date;
import java.util.List;

public interface BuyerItemService {

    List<BuyerItem> getAllBuyerItems();

    List<BuyerItem> findByStatus(String status);

    List<BuyerItem> findByDateAndStatus(Date date);

    List<BuyerItem> findByStatusAndType(String string, String type);

    void saveBuyerItem(BuyerItem buyerItem);

    BuyerItem findById(Long item);

    void removeItem(BuyerItem item);
}
