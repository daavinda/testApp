package market.common.service.impl;

import market.common.orm.model.PendingPayment;
import market.common.orm.model.Seller;
import market.common.orm.repo.SellerRepository;
import market.common.service.PendingPaymentService;
import market.common.service.SellerService;
import market.common.utils.SellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PendingPaymentService pendingPaymentService;

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
        if (seller.getCode() == null) {
            String sellerCode = "S" + String.format("%03d", savedSeller.getId());
            seller.setCode(sellerCode);
            if (seller.getName() == null || seller.getName().equals("")) {
                seller.setName(sellerCode);
            }
            sellerRepository.saveAndFlush(seller);
        }
    }

    @Override
    public List<SellerDto> getAllSellerDetails() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerDto> dtoList = new ArrayList<SellerDto>();
        for (Seller seller : sellerList) {
            SellerDto dto = new SellerDto();
            PendingPayment payment = pendingPaymentService.findBySeller(seller);
            dto.setSeller(seller);
            dto.setDebtAmount(BigDecimal.ZERO);
            if (payment != null) {
                dto.setDebtAmount(payment.getAmount());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
