package market.common.mvc;

import market.common.orm.model.Item;
import market.common.orm.model.Seller;
import market.common.service.ItemService;
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
@RequestMapping("/item")
public class ItemController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    @Qualifier("itemValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("item") Item item, BindingResult result) {
        model.addAttribute("items", itemService.findAllItems());
        return "item-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long itemId) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "common-forms :: #itemForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("item") Item item, BindingResult result) {
        logger.info("Item save/update request received [{}]", item.toString());
        if (result.hasErrors()) {
            return "common-forms :: #itemForm";
        }
        if (item.getName() != null) {
            String itemName = item.getName().trim();
            item.setName(itemName);
        }
        itemService.saveItem(item);
        model.addAttribute("items", itemService.findAllItems());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "item-management :: body";
    }


}
