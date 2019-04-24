package market.common.service;

import market.common.orm.model.SellerExpense;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SellerExpenseService {

    List<SellerExpense> findAll();

    SellerExpense findById(Long expenseId);

    void saveExpense(SellerExpense expense);

    void removeExpense(Long expenseId);

    List<SellerExpense> findByDate(Date date);

    List<SellerExpense> findByDateRange(Date dateFrom, Date dateTo);

}
