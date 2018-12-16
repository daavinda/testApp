package market.common.service.impl;

import market.common.orm.model.Expense;
import market.common.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseService expenseService;

    @Override
    public List<Expense> findAll() {
        return expenseService.findAll();
    }
}
