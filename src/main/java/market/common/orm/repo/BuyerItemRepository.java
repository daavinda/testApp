package market.common.orm.repo;

import market.common.orm.model.BuyerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by devinda on 12/1/18.
 */
public interface BuyerItemRepository extends JpaRepository<BuyerItem, Long> {

    @Query(value = "SELECT * FROM buyer_item u WHERE u.status = ?1",  nativeQuery = true)
    List<BuyerItem> findByStatus(String status);
}
