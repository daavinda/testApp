package market.common.orm.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by devinda on 11/30/18.
 */
@Entity(name = "buyer_item")
public class BuyerItem implements Serializable {

    public enum Status {
        ACTIVE,
        REMOVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "buyer_unit_price")
    private BigDecimal buyerUnitPrice;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Enumerated(EnumType.STRING)
    private BuyerItem.Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "added_by")
    private SystemUser addedUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "removed_by")
    private SystemUser removedUser;

    @Column(name = "sale_type")
    private Long saleType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyerUnitPrice() {
        return buyerUnitPrice;
    }

    public void setBuyerUnitPrice(BigDecimal buyerUnitPrice) {
        this.buyerUnitPrice = buyerUnitPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SystemUser getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(SystemUser addedUser) {
        this.addedUser = addedUser;
    }

    public SystemUser getRemovedUser() {
        return removedUser;
    }

    public void setRemovedUser(SystemUser removedUser) {
        this.removedUser = removedUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSaleType() {
        return saleType;
    }

    public void setSaleType(Long saleType) {
        this.saleType = saleType;
    }
}
