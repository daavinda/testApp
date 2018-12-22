package market.common.service;


import java.math.BigDecimal;


public interface BuyingService {

    void saveBuying(Long sellerId, String itemName, BigDecimal unitPrice, BigDecimal quantity, Long itemType);

    void removeBuying(Long item);
}
