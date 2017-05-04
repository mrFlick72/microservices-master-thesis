package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.RemoveAccountEvent;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoveAccountException extends AbstractAccountException {
    public RemoveAccountException(RemoveAccountEvent event, String msg) {
        super(event, msg);
    }

    public RemoveAccountException(RemoveAccountEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
