package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 11/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveGoodsException extends AbstractException {

    public static final String DEFAULT_MESSAGE  = "error during the saving process";

    public SaveGoodsException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public SaveGoodsException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}

