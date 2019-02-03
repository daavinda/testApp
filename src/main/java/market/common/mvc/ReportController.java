package market.common.mvc;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.Expense;
import market.common.orm.model.Payment;
import market.common.service.*;
import market.common.utils.DailyBuyerReportDto;
import market.common.utils.DailySellerReportDto;
import market.common.utils.MessageResolver;
import market.common.utils.SalesReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/report")
public class ReportController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BuyerItemService buyerItemService;
    @Autowired
    private HelperService helperService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("showTitle", true);
        model.addAttribute("showReports", true);
        model.addAttribute("buyers", buyerService.getAllBuyers());
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        return "report";
    }

    @RequestMapping(value = "/buyerIncome", method = RequestMethod.GET)
    public String buyerIncome(Model model, @RequestParam("date") String date) {
        List<Payment> buyerPayments = paymentService.getBuyerPaymentsByDate(date);
        List<Payment> sellerPayments = paymentService.getSellerPaymentsByDate(date);
        model.addAttribute("reportDate", date);
        model.addAttribute("buyerPayments", buyerPayments);
        model.addAttribute("sellerPayments", sellerPayments);
        model.addAttribute("showDailyIncome", true);
        return "report :: resultsList";
    }

    @RequestMapping(value = "/salesReport", method = RequestMethod.GET)
    public String salesReport(Model model, @RequestParam("from") String from, @RequestParam("to") String to) {
        SalesReportDto dto = reportService.getSalesReportDetails(from, to);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("showDailySales", true);
        model.addAttribute("dto", dto);
        return "report :: resultsList";
    }

    @RequestMapping(value = "/buyerReport", method = RequestMethod.GET)
    public String buyerReport(Model model, @RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("buyer") Long buyerId) {
        DailyBuyerReportDto dto = reportService.getDailyBuyerReportDetails(from, to, buyerId);
        model.addAttribute("dailyBuyerDto", dto);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("showDailyBuyer", true);
        model.addAttribute("buyer", buyerService.getBuyerById(buyerId).getName());
        return "report :: resultsList";
    }

    @RequestMapping(value = "/itemReport", method = RequestMethod.GET)
    public String itemReport(Model model, @RequestParam("date") String date,
                             @RequestParam("item") Long itemId) {
        List<BuyerItem> list = buyerItemService.findByStatusAndDateAndItem(BuyerItem.Status.ACTIVE,
                helperService.formatDate(date), itemId);
        model.addAttribute("buyerItems", list);
        model.addAttribute("date", date);
        model.addAttribute("showDailyItem", true);
        model.addAttribute("item", itemService.findById(itemId).getName());
        return "report :: resultsList";
    }

    @RequestMapping(value = "/sellerReport", method = RequestMethod.GET)
    public String sellerReport(Model model, @RequestParam("from") String from,
                               @RequestParam("to") String to,
                               @RequestParam("seller") Long sellerId) {
        DailySellerReportDto dto = reportService.getDailySellerReportDetails(from, to, sellerId);
        model.addAttribute("dailySellerDto", dto);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("showDailySeller", true);
        model.addAttribute("seller", sellerService.findById(sellerId).getName());
        return "report :: resultsList";
    }

    @RequestMapping(value = "/debtsAndCreditsReport", method = RequestMethod.GET)
    public String debtsAndCreditsReport(Model model) {

        model.addAttribute("reportDetails", reportService.getDebtsAndCreditsReport());
        model.addAttribute("showDebtsAndCredits", true);
        return "report :: resultsList";
    }


    @RequestMapping(value = "/chequePaymentsReport", method = RequestMethod.GET)
    public String chequePaymentsReport(Model model) {
        model.addAttribute("chequePayments", reportService.getBuyerChequePaymentDetails());
        model.addAttribute("showChequePaymentsReport", true);
        return "report :: resultsList";
    }

    @RequestMapping(value = "/expenseReport", method = RequestMethod.GET)
    public String expenseReport(Model model, @RequestParam("dateFrom") String dateFrom,
                                @RequestParam("dateTo") String dateTo) {
        model.addAttribute("expensesDto", reportService.getExpenseReportDetails(dateFrom, dateTo));
        model.addAttribute("showExpenseReport", true);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "report :: resultsList";
    }

    @RequestMapping(value = "/profitReport", method = RequestMethod.GET)
    public String profitReport(Model model, @RequestParam("dateFrom") String dateFrom,
                               @RequestParam("dateTo") String dateTo) {
        model.addAttribute("profitDetails", reportService.getMonthlyProfitDetails(dateFrom, dateTo));
        model.addAttribute("showProfitReport", true);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "report :: resultsList";
    }


}
