package hms.common.validator;

import hms.common.orm.model.Subscriber;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SubscriberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Subscriber.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        //Subscriber subscriber = (Subscriber) target;
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deactivationReason", "validation.required");
    }
}
