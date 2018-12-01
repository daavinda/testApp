package market.common.orm.repo;

import market.common.orm.model.SellerItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by devinda on 12/1/18.
 */
public interface SellerItemRepository extends JpaRepository<SellerItem, Long> {
}
