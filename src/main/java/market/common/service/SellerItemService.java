package market.common.service;


import market.common.orm.model.SellerItem;

import java.util.Date;
import java.util.List;

public interface SellerItemService {

    List<SellerItem> getAllSellerItems();

    List<SellerItem> findByStatus(String status);

    List<SellerItem> findByDateAndStatus(Date date);

    List<SellerItem> findByStatusAndType(String status, String type);

    void saveSellerItem(SellerItem sellerItem);

    SellerItem findById(Long item);

    void removeItem(SellerItem item);
}
