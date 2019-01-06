package market.common.service;

import market.common.orm.model.Buyer;
import market.common.utils.BuyerDto;

import java.math.BigDecimal;
import java.util.List;

public interface BuyerService {

    List<Buyer> getAllBuyers();

    Buyer getBuyerById(Long buyerId);

    void saveBuyer(Buyer buyer);

    List<BuyerDto> getAllBuyerDetails();

    BigDecimal getBuyerCredit(Long buyerId);
}
