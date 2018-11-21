package hms.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "system_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "permission_name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissionList")
    private List<SystemRole> userList;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "depends_on_permission_id")
    Permission dependsOn;

    @Transient
    private boolean checked;

    public Permission() {
    }

    public Permission(Long id) {
        this.id = id;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Permission getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Permission dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public List<SystemRole> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemRole> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "checked=" + checked +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dependsOn=" + dependsOn +
                '}';
    }
}
