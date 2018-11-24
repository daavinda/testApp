package market.common.service.impl;

import market.common.orm.model.Permission;
import market.common.orm.model.SystemRole;
import market.common.orm.repo.PermissionRepository;
import market.common.orm.repo.SystemRoleRepository;
import market.common.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleRepository systemRoleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<SystemRole> getAllUserRoles() {
        return systemRoleRepository.findAll();
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> getAllPermission(SystemRole systemRole) {
        List<Permission> allPermission = permissionRepository.findAll();
        List<Permission> permissionList = systemRole.getPermissionList();
        if (permissionList == null || permissionList.isEmpty()) {
            loadPermissions(systemRole);
        }
        for (Permission permission : allPermission) {
            for (Permission checked : systemRole.getPermissionList()) {
                if (permission.getId() == checked.getId()) {
                    permission.setChecked(true);
                    break;
                }
            }
        }
        return allPermission;
    }

    @Override
    public SystemRole getUserRole(Long id) {
        if (id == null || id == 0) return new SystemRole();
        return systemRoleRepository.findOne(id);
    }

    @Override
    public void saveSystemRole(SystemRole systemRole) {
        loadPermissions(systemRole);
        systemRoleRepository.saveAndFlush(systemRole);
    }

    private void loadPermissions(SystemRole systemRole) {
        for (Long id : systemRole.getCheckedPermissions()) {
            systemRole.getPermissionList().add(permissionRepository.findOne(id));
        }
    }
}
