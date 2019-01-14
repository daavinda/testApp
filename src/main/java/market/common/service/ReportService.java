package market.common.service;

import market.common.orm.model.Payment;
import market.common.utils.DailyBuyerReportDto;
import market.common.utils.DailySellerReportDto;
import market.common.utils.ExpenseReportDto;
import market.common.utils.SalesReportDto;

import java.util.List;

public interface ReportService {

    SalesReportDto getSalesReportDetails(String date);

    DailyBuyerReportDto getDailyBuyerReportDetails(String date, Long buyerId);

    DailySellerReportDto getDailySellerReportDetails(String date, Long sellerId);

    List<Payment> getBuyerChequePaymentDetails();

    ExpenseReportDto getExpenseReportDetails(String dateFrom, String dateTo);

}
