package market.common.service;

import market.common.orm.model.Application;
import market.common.orm.model.Buyer;

import java.util.List;

public interface BuyerService {

    List<Buyer> getAllBuyers();

    Buyer getBuyerById(Long buyerId);

    void saveBuyer(Buyer buyer);
}
