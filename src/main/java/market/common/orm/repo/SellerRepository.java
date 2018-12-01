package market.common.orm.repo;

import market.common.orm.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by devinda on 12/1/18.
 */
public interface SellerRepository extends JpaRepository<PaymentType, Long> {
}
