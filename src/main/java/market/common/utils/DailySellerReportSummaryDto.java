package market.common.utils;

import market.common.orm.model.SellerItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by devinda on 1/12/19.
 */
public class DailySellerReportSummaryDto {

    private List<DailySellerSummary> summaryList;

    private BigDecimal total;

    public List<DailySellerSummary> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<DailySellerSummary> summaryList) {
        this.summaryList = summaryList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
