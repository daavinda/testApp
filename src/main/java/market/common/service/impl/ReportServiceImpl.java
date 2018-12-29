package market.common.service.impl;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.CR;
import market.common.orm.model.SellerItem;
import market.common.service.BuyerItemService;
import market.common.service.CRService;
import market.common.service.ReportService;
import market.common.service.SellerItemService;
import market.common.utils.SalesReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private BuyerItemService buyerItemService;
    @Autowired
    private SellerItemService sellerItemService;
    @Autowired
    private CRService crService;

    @Override
    public SalesReportDto getSalesReportDetails(String date) {

        SalesReportDto dto = new SalesReportDto();
        Date reportDate = new Date();
        try {
            reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<BuyerItem> buyerItemList = buyerItemService.findByDateAndStatus(reportDate);
        List<SellerItem> sellerItemList = sellerItemService.findByDateAndStatus(reportDate);
        List<CR> crList = crService.findByDate(reportDate);

        BigDecimal totalAmountSeller = new BigDecimal(0);
        BigDecimal totalAmountBuyer = new BigDecimal(0);
        BigDecimal totalAmountCr = new BigDecimal(0);
        BigDecimal profitWithCr;
        BigDecimal profitWithoutCr;

        for (BuyerItem item : buyerItemList) {
            totalAmountBuyer = totalAmountBuyer.add(item.getAmount());
        }
        for (SellerItem item : sellerItemList) {
            totalAmountSeller = totalAmountSeller.add(item.getAmount());
        }
        for (CR cr : crList) {
            totalAmountCr = totalAmountCr.add(cr.getAmount());
        }
        profitWithCr = totalAmountBuyer.add(totalAmountCr).subtract(totalAmountSeller);
        profitWithoutCr = totalAmountBuyer.subtract(totalAmountSeller);

        dto.setBuyerItems(buyerItemList);
        dto.setSellerItems(sellerItemList);
        dto.setCrList(crList);
        dto.setTotalAmountBuyer(totalAmountBuyer);
        dto.setTotalAmountSeller(totalAmountSeller);
        dto.setTotalAmountCr(totalAmountCr);
        dto.setProfitWithCr(profitWithCr);
        dto.setProfitWithoutCr(profitWithoutCr);

        return dto;
    }
}
