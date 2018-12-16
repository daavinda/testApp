package market.common.orm.repo;

import market.common.orm.model.Buyer;
import market.common.orm.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
