package market.common.mvc;

import market.common.orm.model.Item;
import market.common.service.ItemService;
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
@RequestMapping("/freezer")
public class FreezerController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(FreezerController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("items", itemService.findInFreezer());
        return "freezer-management";
    }

}
