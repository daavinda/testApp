package market.common.mvc;

import market.common.orm.model.Buyer;
import market.common.orm.model.PendingPayment;
import market.common.orm.model.Seller;
import market.common.service.BuyerService;
import market.common.service.PendingPaymentService;
import market.common.service.SellerService;
import market.common.utils.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/seller")
public class SellerController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    private SellerService sellerService;
    @Autowired
    @Qualifier("sellerValidator")
    private Validator validator;
    @Autowired
    private PendingPaymentService pendingPaymentService;

//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("seller") Seller seller, BindingResult result) {
        model.addAttribute("sellerDetails", sellerService.getAllSellerDetails());
        return "seller-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long sellerId) {
        Seller seller = sellerService.findById(sellerId);
        model.addAttribute("seller", seller);
        return "common-forms :: #sellerForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("seller") Seller seller, BindingResult result) {
        logger.info("Seller save/update request received [{}]", seller.toString());
        if (result.hasErrors()) {
            return "common-forms :: #sellerForm";
        }
        sellerService.saveSeller(seller);
        model.addAttribute("sellerDetails", sellerService.getAllSellerDetails());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "seller-management :: body";
    }

    @RequestMapping(value = "/debt", method = RequestMethod.GET)
    public String addDebt(Model model, @RequestParam("id") Long sellerId) {
        PendingPayment pendingPayment = pendingPaymentService.findBySeller(sellerService.findById(sellerId));
        model.addAttribute("pendingPayment", pendingPayment);
        return "common-forms :: #debtForm";
    }

    @RequestMapping(value = "/saveDebt", method = RequestMethod.POST)
    public String saveDebt(Model model, @ModelAttribute("pendingPayment") PendingPayment pendingPayment) {
        logger.info("Debt save/update request received [{}]", pendingPayment.toString());
        pendingPaymentService.savePayment(pendingPayment);
        model.addAttribute("sellerDetails", sellerService.getAllSellerDetails());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "seller-management :: body";
    }


}
