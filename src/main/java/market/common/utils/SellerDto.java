package market.common.utils;

import market.common.orm.model.Seller;

import java.math.BigDecimal;

/**
 * Created by devinda on 12/22/18.
 */
public class SellerDto {

    private Seller seller;

    private BigDecimal debtAmount;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }
}
