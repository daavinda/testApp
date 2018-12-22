package market.common.orm.repo;

import market.common.orm.model.CR;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CRRepository extends JpaRepository<CR, Long> {
}
