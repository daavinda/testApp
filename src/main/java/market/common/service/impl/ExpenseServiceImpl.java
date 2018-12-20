package market.common.service.impl;

import market.common.orm.model.Expense;
import market.common.orm.model.ExpenseType;
import market.common.orm.repo.ExpenseRepository;
import market.common.orm.repo.ExpenseTypeRepository;
import market.common.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long expenseId) {
        if (expenseId == null || expenseId == 0) return new Expense();
        return expenseRepository.findOne(expenseId);
    }

    @Override
    public void saveExpense(Long expenseType, BigDecimal amount) {
        Expense expense = new Expense();
        expense.setDate(new Date());
        expense.setAmount(amount);
        expense.setExpenseType(expenseTypeRepository.findOne(expenseType));
        expenseRepository.saveAndFlush(expense);
    }

    @Override
    public void removeExpense(Long expenseId) {
        expenseRepository.delete(expenseId);
    }
}
