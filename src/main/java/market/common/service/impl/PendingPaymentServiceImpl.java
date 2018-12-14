package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.PendingPayment;
import market.common.orm.model.Seller;
import market.common.orm.repo.PendingPaymentRepository;
import market.common.service.PendingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PendingPaymentServiceImpl implements PendingPaymentService {

    @Autowired
    private PendingPaymentRepository pendingPaymentRepository;

    @Override
    public PendingPayment findByBuyer(Buyer buyer) {
        return pendingPaymentRepository.findByBuyer(buyer);
    }

    @Override
    public PendingPayment findBySeller(Seller seller) {
        return pendingPaymentRepository.findBySeller(seller);
    }

    @Override
    public void savePayment(PendingPayment payment) {
        pendingPaymentRepository.saveAndFlush(payment);
    }

    @Override
    public void updateWithSelling(Buyer buyer, BigDecimal amount) {
        PendingPayment payment = findByBuyer(buyer);
        BigDecimal newAmount;
        if (payment != null) {
            newAmount = amount.add(payment.getAmount());
            payment.setAmount(newAmount);
            savePayment(payment);
        } else {
            PendingPayment newPayment = new PendingPayment();
            newPayment.setBuyer(buyer);
            newPayment.setAmount(amount);
            savePayment(newPayment);
        }
    }

    @Override
    public void updateWithBuying(Seller seller, BigDecimal amount) {
        PendingPayment payment = findBySeller(seller);
        BigDecimal newAmount;
        if (payment != null) {
            newAmount = amount.add(payment.getAmount());
            payment.setAmount(newAmount);
            savePayment(payment);
        } else {
            PendingPayment newPayment = new PendingPayment();
            newPayment.setSeller(seller);
            newPayment.setAmount(amount);
            savePayment(newPayment);
        }
    }

    @Override
    public void updateWithDeleteSelling(Buyer buyer, BigDecimal amount) {
        PendingPayment payment = findByBuyer(buyer);
        BigDecimal newAmount;
        if (payment != null) {
            newAmount = payment.getAmount().subtract(amount);
            payment.setAmount(newAmount);
            savePayment(payment);
        }
    }
}
