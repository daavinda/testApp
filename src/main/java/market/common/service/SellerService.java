package market.common.service;


import market.common.orm.model.Seller;
import market.common.utils.SellerDto;

import java.util.List;

public interface SellerService {

    List<Seller> getAllSellers();

    Seller findById(Long sellerId);

    void saveSeller(Seller seller);

    List<SellerDto> getAllSellerDetails();

}
