package market.common.service;

import market.common.orm.model.SystemUser;

import java.util.List;

public interface SystemUserService {

    List<SystemUser> getAllUsers();

    SystemUser getSystemUser(Long id);

    void saveSystemUser(SystemUser systemUser);

    void saveSystemUserPassword(SystemUser systemUser);

    SystemUser getCurrentUser();

}
