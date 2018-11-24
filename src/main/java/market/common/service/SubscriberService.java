package market.common.service;



import market.common.orm.model.Subscriber;
import market.common.utils.SubscriberDTO;

import java.util.List;

/**
 * Created by devinda on 4/17/18.
 */
public interface SubscriberService {

    List<Subscriber> getSubscriberDetails(String msisdn);

    void saveSubscriber(Subscriber subscriber);

    String deactivateSubscriber(String msisdn, String applicationName);

    List<SubscriberDTO> getSubscriberStatus(String msisdn);

    String getMsisdnWithPrefix(String msisdn);

}
