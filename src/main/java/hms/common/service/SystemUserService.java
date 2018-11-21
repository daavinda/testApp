package hms.common.service;

import hms.common.orm.model.SystemUser;

import java.util.List;

public interface SystemUserService {

    List<SystemUser> getAllUsers();

    SystemUser getSystemUser(Long id);

    void saveSystemUser(SystemUser systemUser);

    void saveSystemUserPassword(SystemUser systemUser);

}
