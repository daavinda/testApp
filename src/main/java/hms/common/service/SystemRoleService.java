package hms.common.service;

import hms.common.orm.model.Permission;
import hms.common.orm.model.SystemRole;

import java.util.List;

public interface SystemRoleService {

    List<SystemRole> getAllUserRoles();

    List<Permission> getAllPermission();

    List<Permission> getAllPermission(SystemRole systemRole);

    SystemRole getUserRole(Long id);

    void saveSystemRole(SystemRole systemRole);

}
