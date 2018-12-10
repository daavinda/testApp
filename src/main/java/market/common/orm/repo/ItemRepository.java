package market.common.orm.repo;

import market.common.orm.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName(String name);

}
