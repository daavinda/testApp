package market.common.service;

import market.common.orm.model.Item;

import java.util.List;

/**
 * Created by devinda on 12/2/18.
 */
public interface ItemService {

    List<Item> findAllItems();

    Item findById(Long id);

    void saveItem(Item item);
}
