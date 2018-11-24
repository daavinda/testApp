package market.common.validator;

import market.common.orm.model.SystemUser;
import market.common.orm.repo.SystemUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SystemUserValidator implements Validator {

    private final static Logger logger = LoggerFactory.getLogger(SystemUserValidator.class);

    @Autowired
    private SystemUserRepository systemUserRepository;
    @Value("${super.user.username}")
    private String superUserName;

    @Override
    public boolean supports(Class<?> clazz) {
        return SystemUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SystemUser systemUser = (SystemUser) target;

        SystemUser user = systemUserRepository.findByUsername(systemUser.getUsername());

        if (user != null && !(user.getId() == systemUser.getId()) || superUserName.equalsIgnoreCase(systemUser.getUsername())) {
            errors.rejectValue("username", "validation.duplicate");
        }

        if (systemUser.getId() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.required");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "validation.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "validation.required");
        logger.info("Validation errors [{}]", errors.getFieldErrors());
    }
}
