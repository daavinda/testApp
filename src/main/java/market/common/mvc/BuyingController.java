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
        List<SellerItem> sellerItems = sellerItemService.findByStatus(SellerItem.Status.ACTIVE.toString());
        model.addAttribute("sellerItems", sellerItems);
        return "buying-management";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("seller") Long sellerId,
                          @RequestParam("itemType") Long itemType,
                          @RequestParam("item") String itemName,
                          @RequestParam("unitPrice") BigDecimal unitPrice,
                          @RequestParam("quantity") BigDecimal quantity) {

        buyingService.saveBuying(sellerId, itemName, unitPrice, quantity, itemType);

        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        List<SellerItem> sellerItems = sellerItemService.findByStatus(SellerItem.Status.ACTIVE.toString());
        model.addAttribute("sellerItems", sellerItems);

        return "buying-management";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(Model model, @RequestParam("item") Long item) {

        buyingService.removeBuying(item);

        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("items", itemService.findAllItems());
        List<SellerItem> sellerItems = sellerItemService.findByStatus(SellerItem.Status.ACTIVE.toString());
        model.addAttribute("sellerItems", sellerItems);

        return "buying-management";
    }
}
