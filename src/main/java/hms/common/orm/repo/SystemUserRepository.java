package hms.common.orm.repo;

import hms.common.orm.model.SystemUser;
import hms.common.orm.model.SystemUser.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByUsernameAndState(String username, State state);

    SystemUser findById(long id);

    SystemUser findByUsername(String username);
}
