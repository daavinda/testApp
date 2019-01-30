package market.common.orm.repo;

import market.common.orm.model.CR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface CRRepository extends JpaRepository<CR, Long> {

    List<CR> findByDate(Date date);

    List<CR> findByDateBetween(Date from, Date to);
}
