package market.common.validator;

import market.common.orm.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
