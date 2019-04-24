package market.common.utils;

import java.math.BigDecimal;

/**
 * Created by devinda on 4/24/19.
 */
public class DailySellerSummary {

    private String date;
    private BigDecimal amount;
    private BigDecimal expenses;
    private BigDecimal total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
