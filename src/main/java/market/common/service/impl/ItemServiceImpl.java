package market.common.service.impl;

import market.common.orm.model.Item;
import market.common.orm.repo.ItemRepository;
import market.common.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


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
        itemRepository.saveAndFlush(item);
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }
}
