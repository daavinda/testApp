package hms.common.service.impl;

import hms.common.orm.model.Application;
import hms.common.orm.model.Subscriber;
import hms.common.orm.repo.SubscriberRepository;
import hms.common.service.ApplicationService;
import hms.common.service.SubscriberService;
import hms.common.utils.HttpRequestHelper;
import hms.common.utils.SubscriberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by devinda on 4/19/18.
 */
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private ApplicationService applicationService;
    @Value("${subs.search.prefix}")
    private String prefix;

    @Override
    public List<Subscriber> getSubscriberDetails(String msisdn) {
        return subscriberRepository.findByMsisdn(msisdn);
    }

    @Override
    public void saveSubscriber(Subscriber subscriber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        subscriber.setDeactivatedBy(userName);
        subscriber.setDeactivationDate(new Date());
        subscriberRepository.saveAndFlush(subscriber);
    }

    @Override
    public String deactivateSubscriber(String msisdn, String applicationName) {
        Application application = applicationService.findByName(applicationName);
        if (application != null) {
            String applicationUrl = application.getServiceURL();
            String requestUrl = applicationUrl + "?msisdn=" + msisdn + "&action=1";
            HttpRequestHelper requestHelper = new HttpRequestHelper();
            String status = requestHelper.sendRequest(requestUrl);
            return status;
        } else {
            return "0";
        }
    }

    public List<SubscriberDTO> getSubscriberStatus(String msisdn) {
        List<SubscriberDTO> dtoList = new ArrayList<>();
        List<Application> applicationList = applicationService.getAllApplications();
        if (applicationList != null && applicationList.size() > 0) {
            for (Application application : applicationList) {
                String applicationName = application.getApplicationName();
                String applicationUrl = application.getServiceURL();
                String requestUrl = applicationUrl + "?msisdn=" + msisdn + "&action=0";
                HttpRequestHelper requestHelper = new HttpRequestHelper();
                String status = requestHelper.sendRequest(requestUrl);

                SubscriberDTO dto = new SubscriberDTO();
                if ("1".equals(status)) {
                    dto.setStatus("ACTIVE");
                } else if ("0".equals(status)) {
                    dto.setStatus("NOT SUBSCRIBED");
                } else if ("-2".equals(status)) {
                    dto.setStatus("SYSTEM ERROR");
                } else {
                    dto.setStatus("NOT FOUND");
                }
                dto.setApplicationName(applicationName);
                dto.setMsisdn(msisdn);
                dtoList.add(dto);
            }
        }

        return dtoList;
    }

    @Override
    public String getMsisdnWithPrefix(String msisdn) {

        if (prefix != null && !prefix.isEmpty()) {
            String lastNineLetters = msisdn;
            if (msisdn.length() >= 9) {
                lastNineLetters = msisdn.substring(msisdn.length() - 9);
            }
            return prefix + lastNineLetters;
        } else {
            return msisdn;
        }
    }
}
