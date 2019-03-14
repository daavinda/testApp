package market.common.service.impl;

import market.common.orm.model.*;
import market.common.service.*;
import market.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    @Autowired
    private ItemService itemService;
    @Autowired
    private ExpenseTypeService expenseTypeService;

    @Override
    public SalesReportDto getSalesReportDetails(String from, String to) {

        SalesReportDto dto = new SalesReportDto();
        Date fromDate = helperService.formatDate(from);
        Date toDate = helperService.formatDate(to);

        List<BuyerItem> buyerItemList = buyerItemService.findByStatusAndDateBetween(BuyerItem.Status.ACTIVE, fromDate, toDate);
        List<SellerItem> sellerItemList = sellerItemService.findByStatusAndDateBetween(SellerItem.Status.ACTIVE, fromDate, toDate);
        List<CR> crList = crService.findByDate(toDate);
        List<Expense> expenseList = expenseService.findByDateRange(fromDate, toDate);
        List<Item> freezerList = itemService.findByType(Item.ItemType.FREEZER);

        BigDecimal totalAmountSeller = new BigDecimal(0);
        BigDecimal totalAmountBuyer = new BigDecimal(0);
        BigDecimal totalAmountCr = new BigDecimal(0);
        BigDecimal totalAmountExpense = new BigDecimal(0);
        BigDecimal totalAmountFreezer = new BigDecimal(0);
        BigDecimal profitWithCr;
        BigDecimal profitWithoutCr;
        BigDecimal profitWithCrAndFreezer;

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
        for (Item item : freezerList) {
            //if (item.getQuantity().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal amount = item.getQuantity().multiply(item.getUnitPrice());
            totalAmountFreezer = totalAmountFreezer.add(amount);
            //}
        }
        profitWithCr = totalAmountBuyer.add(totalAmountCr).subtract(totalAmountSeller).subtract(totalAmountExpense);
        profitWithCrAndFreezer = totalAmountBuyer.add(totalAmountCr).add(totalAmountFreezer).subtract(totalAmountSeller).subtract(totalAmountExpense);
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
        dto.setProfitWithCrAndFreezer(profitWithCrAndFreezer);
        dto.setFreezerItems(freezerList);
        dto.setTotalAmountFreezer(totalAmountFreezer);

        return dto;
    }

    @Override
    public DailyBuyerReportDto getDailyBuyerReportDetails(String from, String to, Long buyerId) {
        Date fromDate = helperService.formatDate(from);
        Date toDate = helperService.formatDate(to);

        List<Payment> paymentList = paymentService.findByDateBetweenAndBuyer(fromDate, toDate, buyerService.getBuyerById(buyerId));

        BigDecimal totalPayments = new BigDecimal(0);
        if (paymentList != null) {
            for (Payment payment : paymentList) {
                totalPayments = totalPayments.add(payment.getAmount());
            }
        }

        List<BuyerItem> itemList = buyerItemService.findByStatusAndBuyerAndDateBetween(fromDate, toDate, buyerId);

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
        dto.setBuyerPayments(paymentList);
        dto.setTotalPayments(totalPayments);

        return dto;
    }

    @Override
    public DailySellerReportDto getDailySellerReportDetails(String from, String to, Long sellerId) {
        Date fromDate = helperService.formatDate(from);
        Date toDate = helperService.formatDate(to);

        List<SellerItem> itemList = sellerItemService.findByStatusAndSellerAndDateBetween(fromDate, toDate, sellerId);

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
    public ExpenseReportDto getExpenseReportDetails(String dateFrom, String dateTo, Long expenseType) {

        List<Expense> expenseList = expenseService.findByDateBetweenAndExpenseType(helperService.formatDate(dateFrom),
                helperService.formatDate(dateTo), expenseTypeService.findById(expenseType));
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
            SalesReportDto salesReportDto = getSalesReportDetails(simpleDateFormat.format(c.getTime()), simpleDateFormat.format(c.getTime()));

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

    @Override
    public DailyWalletReportDto getDailyWalletReportDetails(String date) {
        Date reportDate = helperService.formatDate(date);
        DailyWalletReportDto dto = new DailyWalletReportDto();

        List<Payment> buyerPayments = paymentService.getBuyerPaymentsByDate(date);
        List<Payment> sellerPayments = paymentService.getSellerPaymentsByDate(date);
        List<Expense> expenseList = expenseService.findByDate(reportDate);

        BigDecimal totalBuyerAmount = new BigDecimal(0);
        BigDecimal totalSellerAmount = new BigDecimal(0);
        BigDecimal totalExpenses = new BigDecimal(0);
        BigDecimal totalAthaMita = new BigDecimal(0);
        BigDecimal totalInWallet = new BigDecimal(0);

        Buyer buyer = buyerService.getBuyerByName("Atha Mita");
        if (buyer != null) {
            List<BuyerItem> buyerItems = buyerItemService.findByDateAndStatusAndBuyer(reportDate, buyer.getId());
            if (buyerItems != null) {
                for (BuyerItem item : buyerItems) {
                    totalAthaMita = totalAthaMita.add(item.getAmount());
                }
                dto.setAthaMitaList(buyerItems);
                dto.setTotalAthaMita(totalAthaMita);
            }
        }

        if (buyerPayments != null) {
            for (Payment payment : buyerPayments) {
                totalBuyerAmount = totalBuyerAmount.add(payment.getAmount());
            }
        }
        if (sellerPayments != null) {
            for (Payment payment : sellerPayments) {
                totalSellerAmount = totalSellerAmount.add(payment.getAmount());
            }
        }
        if (expenseList != null) {
            for (Expense expense : expenseList) {
                totalExpenses = totalExpenses.add(expense.getAmount());
            }
        }
        totalInWallet = totalBuyerAmount.add(totalAthaMita).subtract(totalExpenses).subtract(totalSellerAmount);

        dto.setTotalSellerPayments(totalSellerAmount);
        dto.setTotalBuyerPayments(totalBuyerAmount);
        dto.setTotalExpenses(totalExpenses);
        dto.setTotalInWallet(totalInWallet);

        dto.setExpenses(expenseList);
        dto.setBuyerPayments(buyerPayments);
        dto.setSellerPayments(sellerPayments);

        return dto;
    }
}
