package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.RemoveAccountErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoveAccountException extends AbstractAccountException {
    public static final String DEFAULT_MESSAGE = "delete account error";
    public RemoveAccountException(RemoveAccountErrorEvent event, String msg) {
        super(event, msg);
    }

    public RemoveAccountException(RemoveAccountErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}
