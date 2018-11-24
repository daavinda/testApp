package market.common.service;

import market.common.orm.model.Permission;
import market.common.orm.model.SystemRole;

import java.util.List;

public interface SystemRoleService {

    List<SystemRole> getAllUserRoles();

    List<Permission> getAllPermission();

    List<Permission> getAllPermission(SystemRole systemRole);

    SystemRole getUserRole(Long id);

    void saveSystemRole(SystemRole systemRole);

}
