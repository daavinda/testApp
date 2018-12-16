package market.common.service.impl;

import market.common.orm.model.ExpenseType;
import market.common.orm.repo.ExpenseTypeRepository;
import market.common.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @Override
    public List<ExpenseType> findAll() {
        return expenseTypeRepository.findAll();
    }

    @Override
    public void saveExpenseType(ExpenseType expenseType) {
        expenseTypeRepository.saveAndFlush(expenseType);
    }

    @Override
    public void removeExpenseType(ExpenseType expenseType) {
        expenseTypeRepository.delete(expenseType);
    }
}
