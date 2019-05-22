package market.common.service.impl;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.CR;
import market.common.orm.model.Item;
import market.common.orm.repo.ItemRepository;
import market.common.service.CRService;
import market.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CRService crService;


    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long id) {
        if (id == null || id == 0) return new Item();
        return itemRepository.findOne(id);
    }

    @Override
    public void saveItem(Item item) {
        if (item.getQuantity() == null) {
            item.setQuantity(BigDecimal.ZERO);
        }
        if (item.getUnitPrice() == null) {
            item.setUnitPrice(BigDecimal.ZERO);
        }
        itemRepository.saveAndFlush(item);
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public void updateWithBuying(Item item, BigDecimal quantity) {
        item.setQuantity(item.getQuantity().add(quantity));
        saveItem(item);
    }

    @Override
    public void updateWithRemoveSale(BuyerItem buyerItem) {
        Item item = buyerItem.getItem();
        item.setCrFreezerQty(item.getCrFreezerQty().add(buyerItem.getQuantity()));
        saveItem(item);
    }

    @Override
    public void updateWithSelling(Item item, BigDecimal quantity, Long saleType) {
        if (saleType == null || saleType == 1) {
            item.setQuantity(item.getQuantity().subtract(quantity));
        } else {
            if (item.getCrFreezerQty() != null) {
                item.setCrFreezerQty(item.getCrFreezerQty().subtract(quantity));
            } else {
                item.setCrFreezerQty((new BigDecimal(0)).subtract(quantity));
            }
        }
        saveItem(item);
    }

    @Override
    public List<Item> findByType(Item.ItemType type) {
        return itemRepository.findByType(type);
    }

    @Override
    public List<Item> findByTypeAndNotInCr(Item.ItemType type) {

        List<Item> itemList = itemRepository.findByType(type);
        List<Item> notInCr = new ArrayList<>();
        List<CR> crList = crService.findByDate(new Date());

        if (itemList != null) {
            for (Item item : itemList) {
                boolean found = false;
                if (crList != null && crList.size() > 0) {
                    for (CR cr : crList) {
                        if (item.getId() == cr.getItem().getId()) {
                            found = true;
                        }
                    }
                }
                if (!found) {
                    notInCr.add(item);
                }
            }
        }
        return notInCr;
    }

    @Override
    public List<Item> findInFreezer() {
        List<Item> freezerList = findByType(Item.ItemType.FREEZER);
        List<Item> normalList = findByType(Item.ItemType.NORMAL);

        for (Item item : normalList) {
//            if (item.getCrFreezerQty() != null && item.getCrFreezerQty().compareTo(new BigDecimal(0)) > 0) {
//                freezerList.add(item);
//            }
            if (item.getCrFreezerQty() != null) {
                freezerList.add(item);
            }
        }
        return freezerList;
    }
}
