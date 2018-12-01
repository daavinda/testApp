package market.common.validator;

import market.common.orm.model.Buyer;
import market.common.orm.model.Subscriber;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BuyerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Buyer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
