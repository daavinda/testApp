package market.common.mvc;


import market.common.orm.model.BuyerItem;
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
@RequestMapping("/removal")
public class RemovalController {

    private final static Logger logger = LoggerFactory.getLogger(RemovalController.class);

    @Autowired
    private SellerItemService sellerItemService;
    @Autowired
    private BuyerItemService buyerItemService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load(Model model) {
        List<SellerItem> sellerItems = sellerItemService.findByStatus(SellerItem.Status.REMOVED.toString());
        model.addAttribute("sellerItems", sellerItems);
        List<BuyerItem> buyerItems = buyerItemService.findByStatus(BuyerItem.Status.REMOVED.toString());
        model.addAttribute("buyerItems", buyerItems);
        return "removal";
    }
}
