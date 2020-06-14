package market.common.service;

import market.common.orm.model.Payment;
import market.common.utils.*;

import java.util.List;

public interface ReportService {

    SalesReportDto getSalesReportDetails(String from, String to, String cr);

    DailyBuyerReportDto getDailyBuyerReportDetails(String from, String to, Long buyerId);

    DailySellerReportDto getDailySellerReportDetails(String from, String to, Long sellerId);

    List<Payment> getBuyerChequePaymentDetails();

    ExpenseReportDto getExpenseReportDetails(String dateFrom, String dateTo, Long expenseType);

    MonthlyProfitDto getMonthlyProfitDetails(String dateFrom, String dateTo);

    DebtsAndCreditsReportDto getDebtsAndCreditsReport();

    DailyWalletReportDto getDailyWalletReportDetails(String date);

    DailySellerReportSummaryDto getDailySellerReportSummaryDetails(String from, String to, Long sellerId);
}
