package hms.common.orm.repo;

import hms.common.orm.model.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {

    SystemRole findByRoleName(String roleName);

}
