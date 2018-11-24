package market.common.mvc;

import market.common.orm.model.Application;
import market.common.service.ApplicationService;
import market.common.utils.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by devinda on 4/17/18.
 */

@Controller
@RequestMapping("/application")
public class ApplicationController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    @Qualifier("applicationValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("application") Application application, BindingResult result) {
        model.addAttribute("applications", applicationService.getAllApplications());
        return "app-management";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long appId) {
        Application application = applicationService.getApplicationById(appId);
        model.addAttribute("application", application);
        return "common-forms :: #applicationForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("application") Application application, BindingResult result) {
        logger.info("Application save/update request received [{}]", application.toString());
        if (result.hasErrors()) {
            return "common-forms :: #applicationForm";
        }
        applicationService.saveApplication(application);
        model.addAttribute("applications", applicationService.getAllApplications());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "app-management :: body";
    }
}
