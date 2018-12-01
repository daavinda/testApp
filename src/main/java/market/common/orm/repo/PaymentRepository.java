package market.common.orm.repo;

import market.common.orm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by devinda on 12/1/18.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
