package market.common.orm.repo;

import market.common.orm.model.Buyer;
import market.common.orm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByDate(Date date);

    List<Payment> findByPaymentType(Payment.PaymentType type);

    List<Payment> findByDateBetweenAndBuyer(Date fromDate, Date toDate, Buyer buyer);
}
