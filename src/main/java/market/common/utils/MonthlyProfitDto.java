package market.common.utils;

import java.math.BigDecimal;
import java.util.List;


public class MonthlyProfitDto {

    private List<MonthlyProfit> profitList;
    private BigDecimal totalProfitWithCr;
    private BigDecimal totalProfitWithoutCr;

    public List<MonthlyProfit> getProfitList() {
        return profitList;
    }

    public void setProfitList(List<MonthlyProfit> profitList) {
        this.profitList = profitList;
    }

    public BigDecimal getTotalProfitWithCr() {
        return totalProfitWithCr;
    }

    public void setTotalProfitWithCr(BigDecimal totalProfitWithCr) {
        this.totalProfitWithCr = totalProfitWithCr;
    }

    public BigDecimal getTotalProfitWithoutCr() {
        return totalProfitWithoutCr;
    }

    public void setTotalProfitWithoutCr(BigDecimal totalProfitWithoutCr) {
        this.totalProfitWithoutCr = totalProfitWithoutCr;
    }
}
