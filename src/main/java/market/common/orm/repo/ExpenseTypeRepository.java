package market.common.orm.repo;

import market.common.orm.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
}
