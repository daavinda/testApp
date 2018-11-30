package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "date")
    private Date date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private Item.ItemTye type;

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

    public Item.ItemTye getType() {
        return type;
    }

    public void setType(Item.ItemTye type) {
        this.type = type;
    }
}
