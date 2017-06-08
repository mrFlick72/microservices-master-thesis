package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveCustomerException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "customer data save error";

    public SaveCustomerException(String msg) {
        super( msg);
    }

    public SaveCustomerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}