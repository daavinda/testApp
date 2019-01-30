package market.common.orm.repo;

import market.common.orm.model.Seller;
import market.common.orm.model.SellerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SellerItemRepository extends JpaRepository<SellerItem, Long> {

    @Query(value = "SELECT * FROM seller_item u WHERE u.status = ?1",  nativeQuery = true)
    List<SellerItem> findByStatus(String status);

    @Query(value = "SELECT * FROM seller_item u WHERE u.status = ?1 AND u.type = ?2",  nativeQuery = true)
    List<SellerItem> findByStatusAndType(String status, String type);

    List<SellerItem> findByDateAndStatus(Date date, SellerItem.Status status);

    List<SellerItem> findByDateAndStatusAndSeller(Date date, SellerItem.Status status, Seller seller);

    List<SellerItem> findByStatusAndDateBetween(SellerItem.Status status, Date from, Date to);
}
