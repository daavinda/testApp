package market.common.orm.repo;

import market.common.orm.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
