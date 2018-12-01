package market.common.service;


import market.common.orm.model.Seller;

import java.util.List;

public interface SellerService {

    List<Seller> getAllSellers();

    Seller findById(Long sellerId);

    void saveSeller(Seller seller);

}
