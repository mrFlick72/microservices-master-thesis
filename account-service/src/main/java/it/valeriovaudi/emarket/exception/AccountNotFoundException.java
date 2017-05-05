package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AccountNotFoundEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends AbstractAccountException {
    public static final String DEFAULT_MESSAGE  = "account not found";

    public AccountNotFoundException(AccountNotFoundEvent event, String msg) {
        super(event, msg);
    }

    public AccountNotFoundException(AccountNotFoundEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
