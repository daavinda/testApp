package market.common.mvc;

import market.common.orm.model.Buyer;
import market.common.service.BuyerService;
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
@RequestMapping("/buyer")
public class BuyerController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @Autowired
    private BuyerService buyerService;
    @Autowired
    @Qualifier("buyerValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("buyer") Buyer buyer, BindingResult result) {
        model.addAttribute("buyerDetails", buyerService.getAllBuyerDetails());
        return "buyer-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long buyerId) {
        Buyer buyer = buyerService.getBuyerById(buyerId);
        model.addAttribute("buyer", buyer);
        return "common-forms :: #buyerForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("buyer") Buyer buyer, BindingResult result) {
        logger.info("Buyer save/update request received [{}]", buyer.toString());
        if (result.hasErrors()) {
            return "common-forms :: #buyerForm";
        }
        buyerService.saveBuyer(buyer);
        model.addAttribute("buyerDetails", buyerService.getAllBuyerDetails());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "buyer-management :: body";
    }


}
