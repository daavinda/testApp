package market.common.service.impl;

import market.common.orm.model.*;
import market.common.service.*;
import market.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private HelperService helperService;

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
        List<Expense> expenseList = expenseService.findByDate(reportDate);

        BigDecimal totalAmountSeller = new BigDecimal(0);
        BigDecimal totalAmountBuyer = new BigDecimal(0);
        BigDecimal totalAmountCr = new BigDecimal(0);
        BigDecimal totalAmountExpense = new BigDecimal(0);
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
        for (Expense expense : expenseList) {
            totalAmountExpense = totalAmountExpense.add(expense.getAmount());
        }
        profitWithCr = totalAmountBuyer.add(totalAmountCr).subtract(totalAmountSeller).subtract(totalAmountExpense);
        profitWithoutCr = totalAmountBuyer.subtract(totalAmountSeller).subtract(totalAmountExpense);

        dto.setBuyerItems(buyerItemList);
        dto.setSellerItems(sellerItemList);
        dto.setCrList(crList);
        dto.setTotalAmountBuyer(totalAmountBuyer);
        dto.setTotalAmountSeller(totalAmountSeller);
        dto.setTotalAmountCr(totalAmountCr);
        dto.setExpenseList(expenseList);
        dto.setTotalAmountExpense(totalAmountExpense);
        dto.setProfitWithCr(profitWithCr);
        dto.setProfitWithoutCr(profitWithoutCr);

        return dto;
    }

    @Override
    public DailyBuyerReportDto getDailyBuyerReportDetails(String date, Long buyerId) {

        Date reportDate = new Date();
        try {
            reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<BuyerItem> itemList = buyerItemService.findByDateAndStatusAndBuyer(reportDate, buyerId);

        BigDecimal totalAmount = new BigDecimal(0);
        if (itemList != null) {
            for (BuyerItem item : itemList) {
                totalAmount = totalAmount.add(item.getAmount());
            }
        }

        BigDecimal totalCredits = pendingPaymentService.findByBuyer(buyerService.getBuyerById(buyerId)).getAmount();

        DailyBuyerReportDto dto = new DailyBuyerReportDto();
        dto.setCreditAmount(totalCredits);
        dto.setTotalAmount(totalAmount);
        dto.setItemList(itemList);

        return dto;
    }

    @Override
    public DailySellerReportDto getDailySellerReportDetails(String date, Long sellerId) {
        Date reportDate = new Date();
        try {
            reportDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<SellerItem> itemList = sellerItemService.findByDateAndStatusAndSeller(reportDate, sellerId);

        BigDecimal totalAmount = new BigDecimal(0);
        if (itemList != null) {
            for (SellerItem item : itemList) {
                totalAmount = totalAmount.add(item.getAmount());
            }
        }

        BigDecimal totalCredits = pendingPaymentService.findBySeller(sellerService.findById(sellerId)).getAmount();

        DailySellerReportDto dto = new DailySellerReportDto();
        dto.setCreditAmount(totalCredits);
        dto.setTotalAmount(totalAmount);
        dto.setItemList(itemList);

        return dto;
    }

    @Override
    public List<Payment> getBuyerChequePaymentDetails() {

        List<Payment> paymentList = paymentService.findByPaymentType(Payment.PaymentType.CHEQUE);
        List<Payment> buyerPaymentList = new ArrayList<>();

        if (paymentList != null) {
            for (Payment payment : paymentList) {
                if (payment.getBuyer() != null) {
                    buyerPaymentList.add(payment);
                }
            }
        }


        return buyerPaymentList;
    }

    @Override
    public ExpenseReportDto getExpenseReportDetails(String dateFrom, String dateTo) {

        List<Expense> expenseList = expenseService.findByDateRange(helperService.formatDate(dateFrom),
                helperService.formatDate(dateTo));
        BigDecimal totalAmount = new BigDecimal(0);

        if (expenseList != null) {
            for (Expense expense : expenseList) {
                totalAmount = totalAmount.add(expense.getAmount());
            }
        }

        ExpenseReportDto dto = new ExpenseReportDto();
        dto.setTotalAmount(totalAmount);
        dto.setExpenseList(expenseList);
        return dto;
    }

    @Override
    public MonthlyProfitDto getMonthlyProfitDetails(String dateFrom, String dateTo) {

        Date fromDate = helperService.formatDate(dateFrom);
        Date toDate = helperService.formatDate(dateTo);

        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(toDate);
        c2.add(Calendar.DATE, 1);

        MonthlyProfitDto dto = new MonthlyProfitDto();
        BigDecimal totalProfitWithCr = new BigDecimal(0);
        BigDecimal totalProfitWithoutCr = new BigDecimal(0);
        List<MonthlyProfit> profitList = new ArrayList<>();

        do {
            MonthlyProfit monthlyProfit = new MonthlyProfit();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SalesReportDto salesReportDto = getSalesReportDetails(simpleDateFormat.format(c.getTime()));

            monthlyProfit.setDate(simpleDateFormat.format(c.getTime()));
            monthlyProfit.setProfitWithCr(salesReportDto.getProfitWithCr());
            monthlyProfit.setProfitWithoutCr(salesReportDto.getProfitWithoutCr());
            totalProfitWithCr = totalProfitWithCr.add(salesReportDto.getProfitWithCr());
            totalProfitWithoutCr = totalProfitWithoutCr.add(salesReportDto.getProfitWithoutCr());

            profitList.add(monthlyProfit);
            c.add(Calendar.DATE, 1);
        }
        while (c.getTime().before(c2.getTime()));

        dto.setProfitList(profitList);
        dto.setTotalProfitWithCr(totalProfitWithCr);
        dto.setTotalProfitWithoutCr(totalProfitWithoutCr);
        return dto;
    }

    @Override
    public DebtsAndCreditsReportDto getDebtsAndCreditsReport() {
        DebtsAndCreditsReportDto dto = new DebtsAndCreditsReportDto();
        List<PendingPayment> buyerPayments = pendingPaymentService.findByAllBuyers();
        List<PendingPayment> sellerPayments = pendingPaymentService.findByAllSellers();
        BigDecimal totalBuyerAmount = new BigDecimal(0);
        BigDecimal totalSellerAmount = new BigDecimal(0);

        if (buyerPayments != null) {
            for (PendingPayment pendingPayment : buyerPayments) {
                totalBuyerAmount = totalBuyerAmount.add(pendingPayment.getAmount());
            }
        }
        if (buyerPayments != null) {
            for (PendingPayment pendingPayment : sellerPayments) {
                totalSellerAmount = totalSellerAmount.add(pendingPayment.getAmount());
            }
        }

        dto.setBuyerPayments(buyerPayments);
        dto.setSellerPayments(sellerPayments);
        dto.setTotalBuyerPayments(totalBuyerAmount);
        dto.setTotalSellerPayments(totalSellerAmount);

        return dto;
    }
}
