package market.common.utils;

import market.common.orm.model.Buyer;

import java.math.BigDecimal;

/**
 * Created by devinda on 12/22/18.
 */
public class BuyerDto {

    private Buyer buyer;

    private boolean lowerLimit;

    private boolean upperLimit;

    private BigDecimal creditAmount;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public boolean isLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(boolean lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public boolean isUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(boolean upperLimit) {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }
}
