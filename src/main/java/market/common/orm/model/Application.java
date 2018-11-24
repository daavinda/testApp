package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for applications.
 */
@Entity(name = "application")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String applicationName;
    @Column(name = "description")
    private String applicationDescription;
    @Column(name = "url")
    private String serviceURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicationName='" + applicationName + '\'' +
                ", applicationDescription='" + applicationDescription + '\'' +
                ", serviceURL='" + serviceURL + '\'' +
                '}';
    }
}
