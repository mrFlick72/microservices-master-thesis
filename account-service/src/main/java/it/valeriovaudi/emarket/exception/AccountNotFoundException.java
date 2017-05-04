package it.valeriovaudi.emarket.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends NestedRuntimeException {
    public AccountNotFoundException(String msg) {
        super(msg);
    }

    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
