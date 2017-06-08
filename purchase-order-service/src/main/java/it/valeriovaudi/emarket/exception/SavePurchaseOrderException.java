package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SavePurchaseOrderException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "purchase order didn't found";

    public SavePurchaseOrderException(String msg) {
        super(msg);
    }

    public SavePurchaseOrderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}