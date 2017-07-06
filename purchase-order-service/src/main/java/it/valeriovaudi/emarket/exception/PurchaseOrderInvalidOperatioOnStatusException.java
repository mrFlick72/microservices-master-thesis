package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class PurchaseOrderInvalidOperatioOnStatusException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "operation denied on purchase order";

    public PurchaseOrderInvalidOperatioOnStatusException(String msg) {
        super(msg);
    }

    public PurchaseOrderInvalidOperatioOnStatusException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}