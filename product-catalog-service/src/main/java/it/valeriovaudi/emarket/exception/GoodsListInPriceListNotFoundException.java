package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */


@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoodsListInPriceListNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "goods list didn't found";

    public GoodsListInPriceListNotFoundException(String msg) {
        super(msg);
    }

    public GoodsListInPriceListNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}