package market.common.mvc;


import market.common.service.SellerExpenseService;
import market.common.service.SellerService;
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
@RequestMapping("/sellerExpense")
public class SellerExpenseController {

    private final static Logger logger = LoggerFactory.getLogger(SellerExpenseController.class);

    @Autowired
    private SellerExpenseService sellerExpenseService;
    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("expenses", sellerExpenseService.findAll());
        return "seller-expense-management";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, @RequestParam("seller") Long seller,
                          @RequestParam("amount") BigDecimal amount, @RequestParam("note") String note) {

        sellerExpenseService.saveExpense(seller, amount, note);
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("expenses", sellerExpenseService.findAll());
        return "seller-expense-management";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam("expenseId") Long expenseId) {

        sellerExpenseService.removeExpense(expenseId);
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("expenses", sellerExpenseService.findAll());
        return "seller-expense-management";
    }
}
