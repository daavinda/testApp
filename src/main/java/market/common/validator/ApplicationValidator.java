package market.common.validator;

import market.common.orm.model.Application;
import market.common.orm.repo.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ApplicationValidator implements Validator {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Application application = (Application) target;
        Application appRepo = applicationRepository.findByApplicationName(application.getApplicationName());

        if (appRepo != null && !(application.getId() == appRepo.getId())) {
            errors.rejectValue("applicationName", "validation.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applicationName", "validation.required");
    }
}
