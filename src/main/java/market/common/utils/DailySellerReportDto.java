package market.common.utils;

import market.common.orm.model.SellerExpense;
import market.common.orm.model.SellerItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 1/12/19.
 */
public class DailySellerReportDto {

    List<SellerItem> itemList;

    List<SellerExpense> sellerExpenses;

    BigDecimal totalExpenses;

    BigDecimal totalAmount;

    BigDecimal creditAmount;

    BigDecimal balance;

    public List<SellerItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SellerItem> itemList) {
        this.itemList = itemList;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public List<SellerExpense> getSellerExpenses() {
        return sellerExpenses;
    }

    public void setSellerExpenses(List<SellerExpense> sellerExpenses) {
        this.sellerExpenses = sellerExpenses;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
