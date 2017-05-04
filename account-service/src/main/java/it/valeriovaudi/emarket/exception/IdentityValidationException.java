package it.valeriovaudi.emarket.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IdentityValidationException extends NestedRuntimeException {
    public IdentityValidationException(String msg) {
        super(msg);
    }

    public IdentityValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
