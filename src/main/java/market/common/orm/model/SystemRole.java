package market.common.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "system_user_role")
public class SystemRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "system_role_permission", joinColumns = {
            @JoinColumn(name = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", nullable = false)})
    private List<Permission> permissionList = new ArrayList<Permission>();

    @Transient
    private List<Long> checkedPermissions = new ArrayList<>();

    public SystemRole() {
    }

    public SystemRole(List<Permission> permissionList, String roleName) {
        this.permissionList = permissionList;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getCheckedPermissions() {
        return checkedPermissions;
    }

    public void setCheckedPermissions(List<Long> checkedPermissions) {
        this.checkedPermissions = checkedPermissions;
    }

    @Override
    public String toString() {
        return String.format("SystemRole{id=%d, roleName='%s', permissionList=%s}", id, roleName, checkedPermissions);
    }
}
