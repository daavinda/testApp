package market.common.service;

import market.common.orm.model.Buyer;
import market.common.orm.model.Payment;
import market.common.orm.model.Seller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by devinda on 12/16/18.
 */
public interface PaymentService {

    void saveSellerPayment(Long sellerId, Long paymentType, BigDecimal amount, String chequeDate);

    void saveBuyerPayment(Long buyerId, Long paymentType, BigDecimal amount, String chequeDate);

    List<Payment> findAllPayments();

    void removePayment(Long paymentId);

    List<Payment> findByDate(String date);

    List<Payment> findByDate(Date date);

    List<Payment> getBuyerPaymentsByDate(String date);

    List<Payment> findByDateBetweenAndBuyer(Date fromDate, Date toDate, Buyer buyer);

    List<Payment> getSellerPaymentsByDate(String date);

    List<Payment> findByPaymentType(Payment.PaymentType type);
}
