package market.common.utils;

import market.common.orm.model.Expense;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 1/14/19.
 */
public class ExpenseReportDto {

    private List<Expense> expenseList;
    private BigDecimal totalAmount;

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
