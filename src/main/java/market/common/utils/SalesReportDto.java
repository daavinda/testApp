package market.common.utils;

import market.common.orm.model.BuyerItem;
import market.common.orm.model.CR;
import market.common.orm.model.SellerItem;

import java.math.BigDecimal;
import java.util.List;

public class SalesReportDto {

    private List<SellerItem> sellerItems;

    private BigDecimal totalAmountSeller;

    private List<BuyerItem> buyerItems;

    private BigDecimal totalAmountBuyer;

    private List<CR> crList;

    private BigDecimal totalAmountCr;

    private BigDecimal profitWithCr;

    private BigDecimal profitWithoutCr;

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

    public BigDecimal getProfitWithCr() {
        return profitWithCr;
    }

    public void setProfitWithCr(BigDecimal profitWithCr) {
        this.profitWithCr = profitWithCr;
    }

    public BigDecimal getProfitWithoutCr() {
        return profitWithoutCr;
    }

    public void setProfitWithoutCr(BigDecimal profitWithoutCr) {
        this.profitWithoutCr = profitWithoutCr;
    }
}
