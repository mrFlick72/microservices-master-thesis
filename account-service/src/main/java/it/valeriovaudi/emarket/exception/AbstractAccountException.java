package it.valeriovaudi.emarket.exception;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;
import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Getter
public abstract class AbstractAccountException extends NestedRuntimeException {

    protected final AbstractDomainEvent event;

    public AbstractAccountException(AbstractDomainEvent event, String msg) {
        super(msg);
        this.event = event;
    }

    public AbstractAccountException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(msg, cause);
        this.event = event;
    }
}
