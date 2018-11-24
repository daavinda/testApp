package market.common.service.impl;

import market.common.orm.model.SystemUser;
import market.common.orm.repo.SystemUserRepository;
import market.common.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public List<SystemUser> getAllUsers() {
        return systemUserRepository.findAll();
    }

    @Override
    public SystemUser getSystemUser(Long id) {
        SystemUser systemUser = systemUserRepository.findOne(id);
        if (systemUser == null) {
            return new SystemUser();
        }
        return systemUser;
    }

    @Override
    public void saveSystemUser(SystemUser systemUser) {
        if (systemUser.getId() == 0) {
            systemUser.setState(SystemUser.State.ACTIVE);
            systemUser.setPassword(processHashedPassword(systemUser.getPassword()));
        } else {
            systemUser.setPassword(systemUserRepository.findById(systemUser.getId()).getPassword());
        }
        systemUserRepository.saveAndFlush(systemUser);
    }

    @Override
    public void saveSystemUserPassword(SystemUser systemUser) {
        SystemUser user = systemUserRepository.findById(systemUser.getId());
        user.setPassword(processHashedPassword(systemUser.getPassword()));
        systemUserRepository.saveAndFlush(user);
    }

    private String processHashedPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
