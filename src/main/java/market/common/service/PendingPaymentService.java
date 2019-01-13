package market.common.service;

import market.common.orm.model.Buyer;
import market.common.orm.model.PendingPayment;
import market.common.orm.model.Seller;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 12/12/18.
 */
public interface PendingPaymentService {

    PendingPayment findByBuyer(Buyer buyer);

    PendingPayment findBySeller(Seller seller);

    void savePayment(PendingPayment payment);

    void updateWithSelling(Buyer buyer, BigDecimal amount);

    void updateWithBuying(Seller seller, BigDecimal amount);

    void updateWithDeleteSelling(Buyer buyer, BigDecimal amount);

    void updateWithDeleteBuying(Seller seller, BigDecimal amount);

    void updateWithBuyerPayment(Buyer buyer, BigDecimal amount);

    void updateWithSellerPayment(Seller seller, BigDecimal amount);

    void updateWithDeleteSellerPayment(Seller seller, BigDecimal amount);

    void updateWithDeleteBuyerPayment(Buyer buyer, BigDecimal amount);

    List<PendingPayment> findByAllBuyers();

    List<PendingPayment> findByAllSellers();
}
