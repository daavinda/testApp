package market.common.service.impl;

import market.common.orm.model.Seller;
import market.common.orm.model.SellerExpense;
import market.common.orm.repo.SellerExpenseRepository;
import market.common.service.SellerExpenseService;
import market.common.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SellerExpenseServiceImpl implements SellerExpenseService {

    @Autowired
    private SellerExpenseRepository sellerExpenseRepository;
    @Autowired
    private SellerService sellerService;

    @Override
    public List<SellerExpense> findAll() {
        return sellerExpenseRepository.findAll();
    }

    @Override
    public SellerExpense findById(Long expenseId) {
        if (expenseId == null || expenseId == 0) return new SellerExpense();
        return sellerExpenseRepository.findOne(expenseId);
    }

    @Override
    public void saveExpense(Long seller, BigDecimal amount, String note) {
        SellerExpense expense = new SellerExpense();
        expense.setSeller(sellerService.findById(seller));
        expense.setDate(new Date());
        expense.setAmount(amount);
        expense.setNote(note);
        sellerExpenseRepository.saveAndFlush(expense);
    }

    @Override
    public void removeExpense(Long expenseId) {
        sellerExpenseRepository.delete(expenseId);
    }

    @Override
    public List<SellerExpense> findByDate(Date date) {
        return sellerExpenseRepository.findByDate(date);
    }

    @Override
    public List<SellerExpense> findByDateRange(Date dateFrom, Date dateTo) {
        return sellerExpenseRepository.findByDateRange(dateFrom, dateTo);
    }

    @Override
    public List<SellerExpense> findBySellerAndDateBetween(Seller seller, Date from, Date to) {
        return sellerExpenseRepository.findBySellerAndDateBetween(seller, from, to);
    }
}
