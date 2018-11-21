package hms.common.orm.repo;

import hms.common.orm.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by devinda on 4/17/18.
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    List<Subscriber> findByMsisdn(String msisdn);

}
