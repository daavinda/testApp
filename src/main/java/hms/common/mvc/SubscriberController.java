package hms.common.mvc;

import hms.common.orm.model.Subscriber;
import hms.common.service.SubscriberService;
import hms.common.utils.MessageResolver;
import hms.common.utils.SubscriberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by devinda on 4/18/18.
 */
@Controller
@RequestMapping("/subscriber")
public class SubscriberController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(SubscriberController.class);

    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    @Qualifier("subscriberValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load(Model model) {
        return "subscriber-management";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("search") String subsNumber) {
        subsNumber = subscriberService.getMsisdnWithPrefix(subsNumber);
        List<SubscriberDTO> dtoList = subscriberService.getSubscriberStatus(subsNumber);
        model.addAttribute("dtoList", dtoList);
        List<Subscriber> reasonsList = subscriberService.getSubscriberDetails(subsNumber);
        model.addAttribute("reasonsList", reasonsList);
        model.addAttribute("search", subsNumber);
        model.addAttribute("showTables", true);
        return "subscriber-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long appId,
                          @RequestParam("msisdn") String msisdn,
                          @RequestParam("applicationName") String applicationName) {
        Subscriber subscriber = new Subscriber();
        subscriber.setApplicationName(applicationName);
        subscriber.setMsisdn(msisdn);
        model.addAttribute("subscriber", subscriber);
        model.addAttribute("search", msisdn);
        model.addAttribute("showTables", true);
        return "common-forms :: #deactivationReasonForm";
    }

    @RequestMapping(value = "/deactivate", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("subscriber") Subscriber subscriber, BindingResult result) {

        logger.info("Subscriber deactivation request received [{}]", subscriber.toString());

        String status = subscriberService.deactivateSubscriber(subscriber.getMsisdn(), subscriber.getApplicationName());
        if(status.equals("1")) {
            subscriberService.saveSubscriber(subscriber);
            model.addAttribute("success", getMessage("alert.deactivation.success"));
            logger.info("Successfully deactivated subscriber [{}]", subscriber.getMsisdn());
        } else {
            model.addAttribute("error", getMessage("alert.error"));
        }

        List<SubscriberDTO> dtoList = subscriberService.getSubscriberStatus(subscriber.getMsisdn());
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("reasonsList", subscriberService.getSubscriberDetails(subscriber.getMsisdn()));
        model.addAttribute("search", subscriber.getMsisdn());
        model.addAttribute("showTables", true);
        return "subscriber-management :: resultsList";
    }
}
