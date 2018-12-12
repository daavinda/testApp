package market.common.orm.repo;

import market.common.orm.model.Buyer;
import market.common.orm.model.PendingPayment;
import market.common.orm.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingPaymentRepository extends JpaRepository<PendingPayment, Long> {

    PendingPayment findByBuyer(Buyer buyer);

    PendingPayment findBySeller(Seller seller);
}
