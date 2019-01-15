package market.common.service;

import market.common.orm.model.Payment;
import market.common.utils.*;

import java.util.List;

public interface ReportService {

    SalesReportDto getSalesReportDetails(String date);

    DailyBuyerReportDto getDailyBuyerReportDetails(String date, Long buyerId);

    DailySellerReportDto getDailySellerReportDetails(String date, Long sellerId);

    List<Payment> getBuyerChequePaymentDetails();

    ExpenseReportDto getExpenseReportDetails(String dateFrom, String dateTo);

    MonthlyProfitDto getMonthlyProfitDetails(String dateFrom, String dateTo);

}
