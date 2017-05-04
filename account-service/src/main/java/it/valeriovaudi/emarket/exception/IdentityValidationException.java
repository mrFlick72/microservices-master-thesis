package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.IdentityValidationErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IdentityValidationException extends AbstractAccountException {
    public IdentityValidationException(IdentityValidationErrorEvent event, String msg) {
        super(event, msg);
    }

    public IdentityValidationException(IdentityValidationErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
