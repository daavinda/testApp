package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by devinda on 11/30/18.
 */
@Entity(name = "payment_type")
public class PaymentType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

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
}
