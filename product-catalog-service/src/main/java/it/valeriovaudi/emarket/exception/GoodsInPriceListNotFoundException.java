package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoodsInPriceListNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "goods didn't found";

    public GoodsInPriceListNotFoundException(String msg) {
        super( msg);
    }

    public GoodsInPriceListNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}