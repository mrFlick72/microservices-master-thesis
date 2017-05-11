package it.valeriovaudi.emarket.exception;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import lombok.Getter;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    protected final AbstractDomainEvent event;

    public AbstractException(AbstractDomainEvent event, String msg) {
        super(msg);
        this.event = event;
    }

    public AbstractException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(msg, cause);
        this.event = event;
    }
}
