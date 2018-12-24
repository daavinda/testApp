package market.common.mvc;


import market.common.orm.model.Item;
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
@RequestMapping("/cr")
public class CRController {

    private final static Logger logger = LoggerFactory.getLogger(CRController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private CRService crService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String load(Model model) {
        loadAttributes(model);
        return "cr-management";
    }

    @RequestMapping(value = "/getDetails", method = RequestMethod.GET)
    public String getDetails(Model model, @RequestParam("id") Long id) {
        Item item = itemService.findById(id);
        model.addAttribute("unitPrice", item.getUnitPrice());
        model.addAttribute("quantity", item.getQuantity());
        model.addAttribute("itemId", item.getId());
        loadAttributes(model);
        return "cr-management :: resultsList2";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String saveCr(Model model, @RequestParam("item") Long itemId,
                          @RequestParam("unitPrice") BigDecimal unitPrice,
                          @RequestParam("quantity") BigDecimal quantity) {
        crService.save(itemId, unitPrice, quantity);
        loadAttributes(model);
        return "cr-management";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam("item") Long item) {

        loadAttributes(model);
        return "cr-management";
    }

    private void loadAttributes(Model model) {
        model.addAttribute("items", itemService.findAllItems());
        model.addAttribute("crs", crService.findAll());
    }
}
