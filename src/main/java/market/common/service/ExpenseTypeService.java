package market.common.service;

import market.common.orm.model.ExpenseType;

import java.util.List;


public interface ExpenseTypeService {

    List<ExpenseType> findAll();

    void saveExpenseType(ExpenseType expenseType);

    ExpenseType findById(Long expenseTypeId);

    void removeExpenseType(ExpenseType expenseType);

}
