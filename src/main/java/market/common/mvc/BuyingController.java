package market.common.mvc;


import market.common.orm.model.Item;
import market.common.orm.model.SellerItem;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/buying")
public class BuyingController {

    private final static Logger logger = LoggerFactory.getLogger(BuyingController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerItemService sellerItemService;
    @Autowired
    private BuyingService buyingService;


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String load(Model model) {
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        List<SellerItem> sellerItems = sellerItemService.findByDateAndStatus(new Date());
        model.addAttribute("sellerItems", sellerItems);
        return "buying-management";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("seller") Long sellerId,

                          @RequestParam("item") String itemName,
                          @RequestParam("unitPrice") BigDecimal unitPrice,
                          @RequestParam("quantity") BigDecimal quantity) {

        buyingService.saveBuying(sellerId, itemName, unitPrice, quantity);

        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        List<SellerItem> sellerItems = sellerItemService.findByDateAndStatus(new Date());
        model.addAttribute("sellerItems", sellerItems);

        return "buying-management";
    }

    @RequestMapping(value = "/getDetails", method = RequestMethod.GET)
    public String getDetails(Model model, @RequestParam("item") String item) {
        Item selectedItem = itemService.findByName(item);
        model.addAttribute("itemType", selectedItem.getType().toString());
        return "buying-management :: resultsList2";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam("item") Long item) {

        buyingService.removeBuying(item);

        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        List<SellerItem> sellerItems = sellerItemService.findByDateAndStatus(new Date());
        model.addAttribute("sellerItems", sellerItems);

        return "buying-management";
    }
}
