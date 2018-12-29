package market.common.service.impl;

import market.common.service.BuyerItemService;
import market.common.service.CRService;
import market.common.service.ReportService;
import market.common.service.SellerItemService;
import market.common.utils.SalesReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            reportDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dto.setBuyerItems(buyerItemService.findByDateAndStatus(reportDate));
        dto.setSellerItems(sellerItemService.findByDateAndStatus(reportDate));





        return dto;
    }
}
