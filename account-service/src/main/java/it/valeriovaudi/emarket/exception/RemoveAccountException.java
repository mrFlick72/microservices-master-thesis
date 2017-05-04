package it.valeriovaudi.emarket.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mrflick72 on 04/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoveAccountException extends NestedRuntimeException {
    public RemoveAccountException(String msg) {
        super(msg);
    }

    public RemoveAccountException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
