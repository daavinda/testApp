package market.common.utils;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.Expense;
import market.common.orm.model.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 2/17/19.
 */
public class DailyWalletReportDto {

    private List<Payment> buyerPayments;
    private List<Payment> sellerPayments;
    private List<Expense> expenses;
    private List<BuyerItem> athaMitaList;
    private BigDecimal totalBuyerPayments;
    private BigDecimal totalSellerPayments;
    private BigDecimal totalExpenses;
    private BigDecimal totalAthaMita;
    private BigDecimal totalInWallet;

    public List<Payment> getBuyerPayments() {
        return buyerPayments;
    }

    public void setBuyerPayments(List<Payment> buyerPayments) {
        this.buyerPayments = buyerPayments;
    }

    public List<Payment> getSellerPayments() {
        return sellerPayments;
    }

    public void setSellerPayments(List<Payment> sellerPayments) {
        this.sellerPayments = sellerPayments;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<BuyerItem> getAthaMitaList() {
        return athaMitaList;
    }

    public void setAthaMitaList(List<BuyerItem> athaMitaList) {
        this.athaMitaList = athaMitaList;
    }

    public BigDecimal getTotalBuyerPayments() {
        return totalBuyerPayments;
    }

    public void setTotalBuyerPayments(BigDecimal totalBuyerPayments) {
        this.totalBuyerPayments = totalBuyerPayments;
    }

    public BigDecimal getTotalSellerPayments() {
        return totalSellerPayments;
    }

    public void setTotalSellerPayments(BigDecimal totalSellerPayments) {
        this.totalSellerPayments = totalSellerPayments;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getTotalAthaMita() {
        return totalAthaMita;
    }

    public void setTotalAthaMita(BigDecimal totalAthaMita) {
        this.totalAthaMita = totalAthaMita;
    }

    public BigDecimal getTotalInWallet() {
        return totalInWallet;
    }

    public void setTotalInWallet(BigDecimal totalInWallet) {
        this.totalInWallet = totalInWallet;
    }
}


