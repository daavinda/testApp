package market.common.utils;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by devinda on 1/15/19.
 */
public class MonthlyProfit {

    private String date;
    private BigDecimal profitWithCr;
    private BigDecimal profitWithoutCr;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
