package market.common.mvc;

import market.common.orm.model.Expense;
import market.common.orm.model.ExpenseType;
import market.common.service.ExpenseTypeService;
import market.common.utils.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/expenseType")
public class ExpenseTypeController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(ExpenseTypeController.class);

    @Autowired
    private ExpenseTypeService expenseTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result) {
        model.addAttribute("expenseTypes", expenseTypeService.findAll());
        return "expense-type-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long expenseTypeId) {
        ExpenseType expenseType = expenseTypeService.findById(expenseTypeId);
        model.addAttribute("expenseType", expenseType);
        return "common-forms :: #expenseTypeForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result) {
        logger.info("ExpenseType save/update request received [{}]", expenseType.toString());
        if (result.hasErrors()) {
            return "common-forms :: #expenseTypeForm";
        }
        if (expenseType.getName() != null) {
            String name = expenseType.getName().trim();
            expenseType.setName(name);
        }
        expenseTypeService.saveExpenseType(expenseType);
        model.addAttribute("expenseTypes", expenseTypeService.findAll());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "expense-type-management :: body";
    }


}
