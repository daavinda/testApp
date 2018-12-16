package market.common.service;

import market.common.orm.model.ExpenseType;

import java.util.List;

/**
 * Created by devinda on 12/16/18.
 */
public interface ExpenseTypeService {

    List<ExpenseType> findAll();

    void saveExpenseType(ExpenseType expenseType);

    void removeExpenseType(ExpenseType expenseType);

}
