package market.common.service;

import market.common.orm.model.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 12/2/18.
 */
public interface ItemService {

    List<Item> findAllItems();

    Item findById(Long id);

    void saveItem(Item item);

    Item findByName(String name);

    void updateWithBuying(Item item, BigDecimal quantity);

    void updateWithSelling(Item item, BigDecimal quantity);

    List<Item> findByType(Item.ItemType type);

    List<Item> findByTypeAndNotInCr(Item.ItemType type);
}
