package market.common.utils;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 1/12/19.
 */
public class DailyBuyerReportDto {

    List<BuyerItem> itemList;

    BigDecimal totalAmount;

    BigDecimal creditAmount;

    List<Payment> buyerPayments;

    BigDecimal totalPayments;

    public List<BuyerItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<BuyerItem> itemList) {
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

    public List<Payment> getBuyerPayments() {
        return buyerPayments;
    }

    public void setBuyerPayments(List<Payment> buyerPayments) {
        this.buyerPayments = buyerPayments;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }
}
