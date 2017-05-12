package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 11/05/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceListNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "price list didn't found";

    public PriceListNotFoundException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public PriceListNotFoundException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}

