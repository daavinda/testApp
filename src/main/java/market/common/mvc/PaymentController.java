package market.common.mvc;

import market.common.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private SellerService sellerService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String load(Model model) {

        model.addAttribute("payments", paymentService.findAllPayments());
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("buyers", buyerService.getAllBuyers());
        return "payment-management";
    }

    @RequestMapping(value = "/saveSellerPayment", method = RequestMethod.GET)
    public String saveSellerPayment(Model model, @RequestParam("seller") Long seller,
                                    @RequestParam("paymentType") Long paymentType,
                                    @RequestParam("amount") BigDecimal amount,
                                    @RequestParam("chequeDate") String chequeDate) {

        paymentService.saveSellerPayment(seller, paymentType, amount, chequeDate);
        model.addAttribute("payments", paymentService.findAllPayments());
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("buyers", buyerService.getAllBuyers());
        return "payment-management";
    }

    @RequestMapping(value = "/saveBuyerPayment", method = RequestMethod.GET)
    public String saveBuyerPayment(Model model, @RequestParam("buyer") Long buyer,
                                   @RequestParam("paymentType") Long paymentType,
                                   @RequestParam("amount") BigDecimal amount,
                                   @RequestParam("chequeDate") String chequeDate) {

        paymentService.saveBuyerPayment(buyer, paymentType, amount, chequeDate);
        model.addAttribute("payments", paymentService.findAllPayments());
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("buyers", buyerService.getAllBuyers());
        return "payment-management";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removePayment(Model model, @RequestParam("paymentId") Long paymentId) {

        paymentService.removePayment(paymentId);
        model.addAttribute("payments", paymentService.findAllPayments());
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("buyers", buyerService.getAllBuyers());
        return "payment-management";
    }

}
