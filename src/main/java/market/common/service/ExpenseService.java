package market.common.service;


import market.common.orm.model.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> findAll();



}
