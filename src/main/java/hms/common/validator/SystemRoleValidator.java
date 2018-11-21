package hms.common.validator;

import hms.common.orm.repo.SystemRoleRepository;
import hms.common.orm.model.SystemRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SystemRoleValidator implements Validator {

    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SystemRole.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SystemRole systemRole = (SystemRole) target;

        SystemRole systemRoleRepo = systemRoleRepository.findByRoleName(systemRole.getRoleName());

        if (systemRoleRepo != null && !(systemRole.getId() == systemRoleRepo.getId())) {
            errors.rejectValue("roleName", "validation.duplicate");
        }
        if (systemRole.getCheckedPermissions() == null || systemRole.getCheckedPermissions().isEmpty()) {
            errors.rejectValue("roleName", "validation.required.permissions");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "validation.required");
    }
}
