package market.common.orm.repo;

import market.common.orm.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by devinda on 4/17/18.
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByApplicationName(String applicationName);

}
