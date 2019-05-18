package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.Payment;
import market.common.orm.model.Seller;
import market.common.orm.repo.PaymentRepository;
import market.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Autowired
    private HelperService helperService;


    @Override
    public void saveSellerPayment(Long sellerId, Long paymentType, BigDecimal amount, String chequeDate) {

        Seller seller = sellerService.findById(sellerId);
        pendingPaymentService.updateWithSellerPayment(seller, amount);
        Payment payment = new Payment();
        if (paymentType == 1) {
            payment.setPaymentType(Payment.PaymentType.CASH);
        } else {
            payment.setPaymentType(Payment.PaymentType.CHEQUE);
            payment.setChequeDate(helperService.formatDate(chequeDate));
        }
        payment.setSeller(seller);
        payment.setAmount(amount);
        payment.setDate(new Date());
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void saveBuyerPayment(Long buyerId, Long paymentType, BigDecimal amount, String chequeDate) {

        Buyer buyer = buyerService.getBuyerById(buyerId);
        pendingPaymentService.updateWithBuyerPayment(buyer, amount);
        Payment payment = new Payment();
        payment.setBuyer(buyer);
        if (paymentType == 1) {
            payment.setPaymentType(Payment.PaymentType.CASH);
        } else {
            payment.setPaymentType(Payment.PaymentType.CHEQUE);
            payment.setChequeDate(helperService.formatDate(chequeDate));
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

    @Override
    public List<Payment> findByDate(String date) {
        Date reportDate = new Date();
        try {
            reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paymentRepository.findByDate(reportDate);
    }

    @Override
    public List<Payment> findByDate(Date date) {
        return paymentRepository.findByDate(date);
    }

    @Override
    public List<Payment> getBuyerPaymentsByDate(String date) {

        List<Payment> buyerPaymentList = new ArrayList<>();
        List<Payment> paymentList = findByDate(date);
        if (paymentList != null) {
            for (Payment payment : paymentList) {
                if (payment.getBuyer() != null) {
                    buyerPaymentList.add(payment);
                }
            }
        }
        return buyerPaymentList;
    }

    @Override
    public List<Payment> findByDateBetweenAndBuyer(Date fromDate, Date toDate, Buyer buyer) {
        return paymentRepository.findByDateBetweenAndBuyer(fromDate, toDate, buyer);
    }

    @Override
    public List<Payment> getSellerPaymentsByDate(String date) {
        List<Payment> sellerPaymentList = new ArrayList<>();
        List<Payment> paymentList = findByDate(date);
        if (paymentList != null) {
            for (Payment payment : paymentList) {
                if (payment.getSeller() != null) {
                    sellerPaymentList.add(payment);
                }
            }
        }
        return sellerPaymentList;
    }

    @Override
    public List<Payment> findByPaymentType(Payment.PaymentType type) {
        return paymentRepository.findByPaymentType(type);
    }
}
