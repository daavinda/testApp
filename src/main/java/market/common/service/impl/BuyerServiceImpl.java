package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.model.PendingPayment;
import market.common.orm.repo.BuyerRepository;
import market.common.service.BuyerService;
import market.common.service.PendingPaymentService;
import market.common.utils.BuyerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Value("${buyer.lower.credit.limit}")
    private BigDecimal lowerLimit;
    @Value("${buyer.upper.credit.limit}")
    private BigDecimal upperLimit;


    @Override
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    @Override
    public Buyer getBuyerById(Long buyerId) {
        if (buyerId == null || buyerId == 0) return new Buyer();
        return buyerRepository.findOne(buyerId);
    }

    @Override
    public Buyer getBuyerByName(String name) {
        return buyerRepository.findByName(name);
    }

    @Override
    public void saveBuyer(Buyer buyer) {
        Buyer savedBuyer = buyerRepository.saveAndFlush(buyer);
        if (buyer.getCode() == null) {
            String buyerCode = "B" + String.format("%03d", savedBuyer.getId());
            buyer.setCode(buyerCode);
            if (buyer.getName() == null || buyer.getName().equals("")) {
                buyer.setName(buyerCode);
            }
            buyerRepository.saveAndFlush(buyer);
        }
    }

    @Override
    public List<BuyerDto> getAllBuyerDetails() {
        List<Buyer> buyerList = buyerRepository.findAll();
        List<BuyerDto> dtoList = new ArrayList<BuyerDto>();
        for (Buyer buyer : buyerList) {
            PendingPayment payment = pendingPaymentService.findByBuyer(buyer);
            BuyerDto dto = new BuyerDto();
            dto.setBuyer(buyer);
            dto.setCreditAmount(BigDecimal.ZERO);
            if (payment != null) {
                if (upperLimit.compareTo(payment.getAmount()) < 0) {
                    dto.setUpperLimit(true);
                } else if (lowerLimit.compareTo(payment.getAmount()) < 0) {
                    dto.setLowerLimit(true);
                }
                dto.setCreditAmount(payment.getAmount());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public BigDecimal getBuyerCredit(Long buyerId) {
        PendingPayment payment = pendingPaymentService.findByBuyer(getBuyerById(buyerId));
        return payment.getAmount();
    }


}
