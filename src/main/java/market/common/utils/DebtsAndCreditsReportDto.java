package market.common.utils;

import market.common.orm.model.PendingPayment;

import java.math.BigDecimal;
import java.util.List;

public class DebtsAndCreditsReportDto {

    private List<PendingPayment> buyerPayments;
    private List<PendingPayment> sellerPayments;
    private BigDecimal totalBuyerPayments;
    private BigDecimal totalSellerPayments;

    public List<PendingPayment> getBuyerPayments() {
        return buyerPayments;
    }

    public void setBuyerPayments(List<PendingPayment> buyerPayments) {
        this.buyerPayments = buyerPayments;
    }

    public List<PendingPayment> getSellerPayments() {
        return sellerPayments;
    }

    public void setSellerPayments(List<PendingPayment> sellerPayments) {
        this.sellerPayments = sellerPayments;
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
}
