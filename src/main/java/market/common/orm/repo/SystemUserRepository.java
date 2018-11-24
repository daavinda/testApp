package market.common.orm.repo;

import market.common.orm.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByUsernameAndState(String username, SystemUser.State state);

    SystemUser findById(long id);

    SystemUser findByUsername(String username);
}
