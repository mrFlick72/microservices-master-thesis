package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.event.model.AccountValidationErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.AccountValidationException;
import it.valeriovaudi.emarket.model.Account;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mrflick72 on 04/05/17.
 */

@Data
@Component
public class AccountDataValidationServiceImpl implements AccountDataValidationService {

    public static final int TAX_CODE_LENGTH= 16;
    public static final String ACCOUNT_VALIDATION_EXCEPTION_MESSAGE = "The Account %s hasn't satisfies the constraints";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void validate(String correlationId, Account account) {
        Map<String, String> errors = new HashMap<>();

        String userName = account.getUserName();
        String password = account.getPassword();
        String taxCode = account.getTaxCode();
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String mail = account.getMail();

        validateNotNullAndNotEmpty("userName", userName, "AccountDataValidationService.Account.userName",errors);
        validateNotNullAndNotEmpty("password", password, "AccountDataValidationService.Account.password",errors);
        validateNotNullAndNotEmpty("firstName", firstName, "AccountDataValidationService.Account.firstName",errors);
        validateNotNullAndNotEmpty("lastName", lastName, "AccountDataValidationService.Account.lastName",errors);
        validateTaxCode("taxCode", taxCode, "AccountDataValidationService.Account.taxCode",errors);
        validateMail("mail", mail, "AccountDataValidationService.Account.mail",errors);

        manageError(correlationId,userName,errors);
    }

    @Override
    public void validateUserName(String correlationId, String userName) {
        Map<String, String> errors = new HashMap<>();

        validateNotNullAndNotEmpty("userName", userName, "AccountDataValidationService.Account.userName",errors);
        manageError(correlationId,userName,errors);
    }

    private void manageError(String correlationId, String userName, Map<String,String> errors) {
        if (errors.size() > 0) {
            AccountValidationErrorEvent accountValidationErrorEvent =
                    eventDomainPubblishService.publishAccountValidationErrorEvent(correlationId, errors);
            throw new AccountValidationException(accountValidationErrorEvent,
                    String.format(ACCOUNT_VALIDATION_EXCEPTION_MESSAGE, userName));
        }
    }

    private void validateMail(String key,String value,String validationMessageKey, Map<String,String> errors){
        boolean inError = value == null;

        if(!inError){
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(value);
            inError = !matcher.matches();
        }
        if(inError){
            errors.put(key, messageSource.getMessage(validationMessageKey,new Object[]{}, Locale.ENGLISH));
        }
    }

    private void validateTaxCode(String key,String value,String validationMessageKey, Map<String,String> errors){
        boolean inError = value == null || value.length() != TAX_CODE_LENGTH;

        if(inError){
            errors.put(key, messageSource.getMessage(validationMessageKey,new Object[]{}, Locale.ENGLISH));
        }
    }

    private void validateNotNullAndNotEmpty(String key,String value,String validationMessageKey, Map<String,String> errors){
        if (value == null || "".equals(value)){
            errors.put(key, messageSource.getMessage(validationMessageKey,new Object[]{}, Locale.ENGLISH));
        }
    }
}