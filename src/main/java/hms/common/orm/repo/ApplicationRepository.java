package hms.common.orm.repo;

import hms.common.orm.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by devinda on 4/17/18.
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByApplicationName(String applicationName);

}
