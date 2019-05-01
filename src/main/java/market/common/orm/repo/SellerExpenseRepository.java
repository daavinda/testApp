package market.common.orm.repo;

import market.common.orm.model.Expense;
import market.common.orm.model.ExpenseType;
import market.common.orm.model.Seller;
import market.common.orm.model.SellerExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface SellerExpenseRepository extends JpaRepository<SellerExpense, Long> {

    List<SellerExpense> findByDate(Date date);

    @Query(value = "SELECT * FROM expense u WHERE u.date >= ?1 AND u.date <= ?2", nativeQuery = true)
    List<SellerExpense> findByDateRange(Date dateFrom, Date dateTo);

    List<SellerExpense> findBySellerAndDateBetween(Seller seller, Date from, Date to);
}
