package market.common.mvc;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.Item;
import market.common.service.BuyerItemService;
import market.common.service.BuyerService;
import market.common.service.ItemService;
import market.common.service.SellingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/selling")
public class SellingController {

    private final static Logger logger = LoggerFactory.getLogger(SellingController.class);

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SellingService sellingService;
    @Autowired
    private BuyerItemService buyerItemService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("buyers", buyerService.getAllBuyers());
        model.addAttribute("items", itemService.findAllItems());
        List<BuyerItem> buyerItems = buyerItemService.findByDateAndStatus(new Date());
        model.addAttribute("buyerItems", buyerItems);
        return "selling-management";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("buyer") Long buyerId,
                          @RequestParam("item") String itemName,
                          @RequestParam("unitPrice") BigDecimal unitPrice,
                          @RequestParam("quantity") BigDecimal quantity,
                          @RequestParam("saleType") String saleType) {

        sellingService.saveSale(buyerId, itemName, unitPrice, quantity, Long.parseLong(saleType));

        model.addAttribute("buyers", buyerService.getAllBuyers());
        model.addAttribute("items", itemService.findAllItems());
        List<BuyerItem> buyerItems = buyerItemService.findByDateAndStatus(new Date());
        model.addAttribute("buyerItems", buyerItems);

        return "selling-management";
    }

    @RequestMapping(value = "/getDetails", method = RequestMethod.GET)
    public String getDetails(Model model, @RequestParam("item") String item) {
        Item selectedItem = itemService.findByName(item);
        model.addAttribute("itemType", selectedItem.getType().toString());
        return "selling-management :: resultsList2";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam("item") Long item) {

        sellingService.removeSale(item);

        model.addAttribute("buyers", buyerService.getAllBuyers());
        model.addAttribute("items", itemService.findAllItems());
        List<BuyerItem> buyerItems = buyerItemService.findByDateAndStatus(new Date());
        model.addAttribute("buyerItems", buyerItems);

        return "selling-management";
    }
}
