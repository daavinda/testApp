package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by devinda on 11/30/18.
 */
@Entity(name = "seller_item")
public class SellerItem implements Serializable {

    public enum Status {
        ACTIVE,
        REMOVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "seller_unit_price")
    private BigDecimal sellerUnitPrice;

    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    private Item.ItemType type;

    @Enumerated(EnumType.STRING)
    private SellerItem.Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "added_by")
    private SystemUser addedUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "removed_by")
    private SystemUser removedUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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

    public BigDecimal getSellerUnitPrice() {
        return sellerUnitPrice;
    }

    public void setSellerUnitPrice(BigDecimal sellerUnitPrice) {
        this.sellerUnitPrice = sellerUnitPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Item.ItemType getType() {
        return type;
    }

    public void setType(Item.ItemType type) {
        this.type = type;
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
}
