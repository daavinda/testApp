package market.common.service.impl;

import market.common.orm.model.Seller;
import market.common.orm.repo.SellerRepository;
import market.common.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller findById(Long sellerId) {
        if (sellerId == null || sellerId == 0) return new Seller();
        return sellerRepository.findOne(sellerId);
    }

    @Override
    public void saveSeller(Seller seller) {
        Seller savedSeller = sellerRepository.saveAndFlush(seller);
        if(seller.getCode() == null) {
            String sellerCode = "S" + String.format("%03d", savedSeller.getId());
            seller.setCode(sellerCode);
            sellerRepository.saveAndFlush(seller);
        }
    }
}
