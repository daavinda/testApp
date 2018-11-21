package hms.common.mvc;

import hms.common.orm.model.SystemUser;
import hms.common.service.SystemRoleService;
import hms.common.service.SystemUserService;
import hms.common.utils.MessageResolver;
import hms.common.utils.SystemValidation;
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

@Controller
@RequestMapping("/system")
public class SystemUserController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(SystemUserController.class);

    @Autowired
    @Qualifier("systemUserValidator")
    private Validator validator;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemRoleService systemRoleService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("systemUser") SystemUser systemUser, BindingResult result) {
        model.addAttribute("systemUsers", systemUserService.getAllUsers());
        return "system-user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long roleId) {
        SystemUser systemUser = systemUserService.getSystemUser(roleId);
        model.addAttribute("systemUser", systemUser);
        loadData(model);
        return "common-forms :: #systemUserForm";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("systemUser") SystemUser systemUser, BindingResult result) {
        logger.info("User role save/update received [{}]", systemUser.toString());
        if (result.hasErrors()) {
            loadData(model);
            return "common-forms :: #systemUserForm";
        }
        systemUserService.saveSystemUser(systemUser);
        model.addAttribute("systemUsers", systemUserService.getAllUsers());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "system-user :: body";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.GET)
    public String loadPassword(Model model, @RequestParam("id") Long id) {
        SystemUser systemUser = systemUserService.getSystemUser(id);
        model.addAttribute("systemUser", systemUser);
        return "common-forms :: #changePasswordForm";
    }

    @RequestMapping(value = "/user/save/password", method = RequestMethod.POST)
    public String savePassword(Model model, @Validated @ModelAttribute("systemUser") SystemUser systemUser, BindingResult result) {
        validatePassword(systemUser, result);
        if (result.hasErrors()) {
            model.addAttribute("systemUser", systemUser);
            return "common-forms :: #changePasswordForm";
        }
        systemUserService.saveSystemUserPassword(systemUser);
        model.addAttribute("systemUsers", systemUserService.getAllUsers());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "system-user :: body";
    }

    private void validatePassword(SystemUser systemUser, BindingResult result) {
        if (!systemUser.getPassword().equalsIgnoreCase(systemUser.getConfirmPassword())) {
            result.rejectValue("password", "validation.mismatch");
        }
        if (!SystemValidation.isValidPassword(systemUser.getPassword())) {
            result.rejectValue("password", "validation.comment");
        }
    }

    private void loadData(Model model) {
        model.addAttribute("roles", systemRoleService.getAllUserRoles());
    }
}
