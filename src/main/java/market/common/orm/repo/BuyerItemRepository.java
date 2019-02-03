package market.common.orm.repo;

import market.common.orm.model.Buyer;
import market.common.orm.model.BuyerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by devinda on 12/1/18.
 */
public interface BuyerItemRepository extends JpaRepository<BuyerItem, Long> {

    @Query(value = "SELECT * FROM buyer_item u WHERE u.status = ?1",  nativeQuery = true)
    List<BuyerItem> findByStatus(String status);

    @Query(value = "SELECT * FROM buyer_item u WHERE u.status = ?1 AND u.type = ?2",  nativeQuery = true)
    List<BuyerItem> findByStatusAndType(String status, String type);

    List<BuyerItem> findByDateAndStatus(Date date, BuyerItem.Status status);

    List<BuyerItem> findByDateAndStatusAndBuyer(Date date, BuyerItem.Status active, Buyer buyer);

    List<BuyerItem> findByStatusAndBuyerAndDateBetween(BuyerItem.Status active, Buyer buyer, Date from, Date to);

    List<BuyerItem> findByStatusAndDateBetween(BuyerItem.Status status, Date from, Date to);
}
