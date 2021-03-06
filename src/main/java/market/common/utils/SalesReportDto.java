package market.common.utils;

import market.common.orm.model.*;

import java.math.BigDecimal;
import java.util.List;

public class SalesReportDto {

    private List<SellerItem> sellerItems;

    private BigDecimal totalAmountSeller;

    private List<BuyerItem> buyerItems;

    private BigDecimal totalAmountBuyer;

    private List<CR> crList;

    private BigDecimal totalAmountCr;

    private List<Expense> expenseList;

    private BigDecimal totalAmountExpense;

    private List<Item> freezerItems;

    private BigDecimal totalAmountFreezer;

    private BigDecimal profitWithFreezer;

    private BigDecimal profit;

    private BigDecimal yesterdayCr;

    private BigDecimal todayFreezerSelling;

    public List<SellerItem> getSellerItems() {
        return sellerItems;
    }

    public void setSellerItems(List<SellerItem> sellerItems) {
        this.sellerItems = sellerItems;
    }

    public BigDecimal getTotalAmountSeller() {
        return totalAmountSeller;
    }

    public void setTotalAmountSeller(BigDecimal totalAmountSeller) {
        this.totalAmountSeller = totalAmountSeller;
    }

    public List<BuyerItem> getBuyerItems() {
        return buyerItems;
    }

    public void setBuyerItems(List<BuyerItem> buyerItems) {
        this.buyerItems = buyerItems;
    }

    public BigDecimal getTotalAmountBuyer() {
        return totalAmountBuyer;
    }

    public void setTotalAmountBuyer(BigDecimal totalAmountBuyer) {
        this.totalAmountBuyer = totalAmountBuyer;
    }

    public List<CR> getCrList() {
        return crList;
    }

    public void setCrList(List<CR> crList) {
        this.crList = crList;
    }

    public BigDecimal getTotalAmountCr() {
        return totalAmountCr;
    }

    public void setTotalAmountCr(BigDecimal totalAmountCr) {
        this.totalAmountCr = totalAmountCr;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public BigDecimal getTotalAmountExpense() {
        return totalAmountExpense;
    }

    public void setTotalAmountExpense(BigDecimal totalAmountExpense) {
        this.totalAmountExpense = totalAmountExpense;
    }

    public List<Item> getFreezerItems() {
        return freezerItems;
    }

    public void setFreezerItems(List<Item> freezerItems) {
        this.freezerItems = freezerItems;
    }

    public BigDecimal getTotalAmountFreezer() {
        return totalAmountFreezer;
    }

    public void setTotalAmountFreezer(BigDecimal totalAmountFreezer) {
        this.totalAmountFreezer = totalAmountFreezer;
    }

    public BigDecimal getProfitWithFreezer() {
        return profitWithFreezer;
    }

    public void setProfitWithFreezer(BigDecimal profitWithFreezer) {
        this.profitWithFreezer = profitWithFreezer;
    }

    public BigDecimal getYesterdayCr() {
        return yesterdayCr;
    }

    public void setYesterdayCr(BigDecimal yesterdayCr) {
        this.yesterdayCr = yesterdayCr;
    }

    public BigDecimal getTodayFreezerSelling() {
        return todayFreezerSelling;
    }

    public void setTodayFreezerSelling(BigDecimal todayFreezerSelling) {
        this.todayFreezerSelling = todayFreezerSelling;
    }
}
