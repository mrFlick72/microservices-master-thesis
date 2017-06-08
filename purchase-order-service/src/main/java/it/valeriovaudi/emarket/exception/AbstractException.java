package it.valeriovaudi.emarket.exception;

import lombok.Getter;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public AbstractException(String msg) {
        super(msg);
    }

    public AbstractException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public abstract String getDefaultMessage();
}
