package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AccountValidationErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountValidationException extends AbstractAccountException {
    public AccountValidationException(AccountValidationErrorEvent event, String msg) {
        super(event, msg);
    }

    public AccountValidationException(AccountValidationErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
