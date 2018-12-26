package market.common.mvc;

import market.common.orm.model.Payment;
import market.common.service.PaymentService;
import market.common.utils.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/report")
public class ReportController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("showTitle", true);
        model.addAttribute("showReports", true);
        return "report";
    }

    @RequestMapping(value = "/buyerIncome", method = RequestMethod.GET)
    public String buyerIncome(Model model) {
        List<Payment> payments = paymentService.findByDate(new Date());
        model.addAttribute("payments", payments);
        model.addAttribute("showBuyerIncome", true);
        return "report :: resultsList";
    }



}
