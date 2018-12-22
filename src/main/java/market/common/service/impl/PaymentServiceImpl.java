package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.Payment;
import market.common.orm.model.Seller;
import market.common.orm.repo.PaymentRepository;
import market.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private BuyerService buyerService;


    @Override
    public void saveSellerPayment(Long sellerId, Long paymentType, BigDecimal amount) {

        Seller seller = sellerService.findById(sellerId);
        pendingPaymentService.updateWithSellerPayment(seller, amount);
        Payment payment = new Payment();
        if (paymentType == 1) {
            payment.setPaymentType(Payment.PaymentType.CASH);
        } else {
            payment.setPaymentType(Payment.PaymentType.CHEQUE);
        }
        payment.setSeller(seller);
        payment.setAmount(amount);
        payment.setDate(new Date());
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void saveBuyerPayment(Long buyerId, Long paymentType, BigDecimal amount) {

        Buyer buyer = buyerService.getBuyerById(buyerId);
        pendingPaymentService.updateWithBuyerPayment(buyer, amount);
        Payment payment = new Payment();
        payment.setBuyer(buyer);
        if (paymentType == 1) {
            payment.setPaymentType(Payment.PaymentType.CASH);
        } else {
            payment.setPaymentType(Payment.PaymentType.CHEQUE);
        }
        payment.setAmount(amount);
        payment.setDate(new Date());
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void removePayment(Long paymentId) {
        Payment payment = paymentRepository.findOne(paymentId);
        if (payment.getBuyer() != null) {
            pendingPaymentService.updateWithDeleteBuyerPayment(payment.getBuyer(), payment.getAmount());
        } else if (payment.getSeller() != null) {
            pendingPaymentService.updateWithDeleteSellerPayment(payment.getSeller(), payment.getAmount());
        }
        paymentRepository.delete(payment);
    }
}