package market.common.service;


import market.common.orm.model.Expense;
import market.common.orm.model.ExpenseType;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    List<Expense> findAll();

    Expense findById(Long expenseId);

    void saveExpense(Long type, BigDecimal amount);

    void removeExpense(Long expenseId);

}
