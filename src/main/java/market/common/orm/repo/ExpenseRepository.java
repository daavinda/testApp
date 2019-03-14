package market.common.orm.repo;

import market.common.orm.model.Expense;
import market.common.orm.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDate(Date date);

    @Query(value = "SELECT * FROM expense u WHERE u.date >= ?1 AND u.date <= ?2",  nativeQuery = true)
    List<Expense> findByDateRange(Date dateFrom, Date dateTo);

    List<Expense> findByDateBetweenAndExpenseType(Date dateFrom, Date dateTo, ExpenseType expenseType);
}
