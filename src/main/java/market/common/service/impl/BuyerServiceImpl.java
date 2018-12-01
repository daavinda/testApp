package market.common.service.impl;

import market.common.orm.model.Buyer;
import market.common.orm.repo.BuyerRepository;
import market.common.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

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
    public void saveBuyer(Buyer buyer) {
        Buyer savedBuyer = buyerRepository.saveAndFlush(buyer);
        if(buyer.getCode() == null) {
            String buyerCode = "B" + String.format("%03d", savedBuyer.getId());
            buyer.setCode(buyerCode);
            buyerRepository.saveAndFlush(buyer);
        }
    }
}
