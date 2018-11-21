package hms.common.mvc;

import hms.common.orm.model.Permission;
import hms.common.orm.model.SystemRole;
import hms.common.service.SystemRoleService;
import hms.common.utils.MessageResolver;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemRoleController extends MessageResolver {

    private final static Logger logger = LoggerFactory.getLogger(SystemRoleController.class);

    @Autowired
    @Qualifier("systemRoleValidator")
    private Validator validator;
    @Autowired
    private SystemRoleService systemRoleService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public String load(Model model, @ModelAttribute("userRole") SystemRole systemRole, BindingResult result) {
        model.addAttribute("userRoles", systemRoleService.getAllUserRoles());
        return "user-role";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String loadOne(Model model, @RequestParam("id") Long roleId) {
        SystemRole userRole = systemRoleService.getUserRole(roleId);
        List<Permission> updatePermissions = systemRoleService.getAllPermission(userRole);
        model.addAttribute("userRole", userRole);
        model.addAttribute("permissions", updatePermissions);
        return "common-forms :: #userRoleForm";
    }

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String save(Model model, @Validated @ModelAttribute("userRole") SystemRole systemRole, BindingResult result) {
        logger.info("User role save/update received [{}]", systemRole.toString());
        if (result.hasErrors()) {
            model.addAttribute("permissions", systemRoleService.getAllPermission(systemRole));
            return "common-forms :: #userRoleForm";
        }
        systemRoleService.saveSystemRole(systemRole);
        model.addAttribute("userRoles", systemRoleService.getAllUserRoles());
        model.addAttribute("success", getMessage("alert.save.success"));
        return "user-role :: body";
    }
}
