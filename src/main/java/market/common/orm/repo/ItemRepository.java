package market.common.orm.repo;

import market.common.orm.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

    List<Item> findByType(Item.ItemType type);

}
