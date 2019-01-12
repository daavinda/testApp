package market.common.utils;

import market.common.orm.model.SellerItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 1/12/19.
 */
public class DailySellerReportDto {

    List<SellerItem> itemList;

    BigDecimal totalAmount;

    BigDecimal creditAmount;

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
}
