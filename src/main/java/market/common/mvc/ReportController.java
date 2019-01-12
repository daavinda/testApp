package market.common.mvc;

import market.common.orm.model.Payment;
import market.common.service.BuyerService;
import market.common.service.PaymentService;
import market.common.service.ReportService;
import market.common.utils.DailyBuyerReportDto;
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

import java.util.Date;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("showTitle", true);
        model.addAttribute("showReports", true);
        model.addAttribute("buyers", buyerService.getAllBuyers());
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
    public String salesReport(Model model, @RequestParam("date") String date) {
        SalesReportDto dto = reportService.getSalesReportDetails(date);
        model.addAttribute("reportDate", date);
        model.addAttribute("showDailySales", true);
        model.addAttribute("dto", dto);
        return "report :: resultsList";
    }

    @RequestMapping(value = "/buyerReport", method = RequestMethod.GET)
    public String buyerReport(Model model, @RequestParam("date") String date,
                              @RequestParam("buyer") Long buyerId) {
        DailyBuyerReportDto dto = reportService.getDailyBuyerReportDetails(date, buyerId);
        model.addAttribute("dailyBuyerDto", dto);
        model.addAttribute("reportDate", date);
        model.addAttribute("showDailyBuyer", true);
        model.addAttribute("buyer", buyerService.getBuyerById(buyerId).getName());
        return "report :: resultsList";
    }


}
