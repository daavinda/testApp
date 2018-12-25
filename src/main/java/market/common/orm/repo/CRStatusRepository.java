package market.common.orm.repo;

import market.common.orm.model.CRStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface CRStatusRepository extends JpaRepository<CRStatus, Long> {

    CRStatus findByDate(Date date);

}
