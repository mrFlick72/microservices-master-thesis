package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveShipmentException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "shipment data save error";

    public SaveShipmentException(String msg) {
        super( msg);
    }

    public SaveShipmentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}