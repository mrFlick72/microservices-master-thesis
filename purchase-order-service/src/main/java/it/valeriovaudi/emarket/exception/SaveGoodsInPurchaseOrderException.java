package it.valeriovaudi.emarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vvaudi on 12/05/17.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveGoodsInPurchaseOrderException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "purchase order data save error";

    public SaveGoodsInPurchaseOrderException(String msg) {
        super( msg);
    }

    public SaveGoodsInPurchaseOrderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}