package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by devinda on 11/30/18.
 */
@Entity(name = "buyer_item")
public class BuyerItem implements Serializable {

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
    private float quantity;

    @Column(name = "buyer_unit_price")
    private BigDecimal buyerUnitPrice;

    @Column(name = "date")
    private Date date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private Item.ItemType type;

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

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Item.ItemType getType() {
        return type;
    }

    public void setType(Item.ItemType type) {
        this.type = type;
    }
}
