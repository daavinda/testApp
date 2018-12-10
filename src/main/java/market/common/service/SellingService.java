package market.common.service;


import java.math.BigDecimal;

/**
 * Created by devinda on 12/9/18.
 */
public interface SellingService {

    void saveSale(Long buyerId, String itemName, BigDecimal unitPrice, BigDecimal quantity);
}
