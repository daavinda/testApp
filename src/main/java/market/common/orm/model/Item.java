package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "item")
public class Item implements Serializable {

    public enum ItemType {
        NORMAL,
        FREEZER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "cr_freezer_quantity")
    private BigDecimal crFreezerQty;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public BigDecimal getCrFreezerQty() {
        return crFreezerQty;
    }

    public void setCrFreezerQty(BigDecimal crFreezerQty) {
        this.crFreezerQty = crFreezerQty;
    }

    @Override
    public String toString() {
        return String.format("Item{id=%d, name='%s', unitPrice='%s', quantity='%s', crFreezerQuantity='%s', type='%s'}", id, name, unitPrice, quantity, crFreezerQty, type);
    }
}
