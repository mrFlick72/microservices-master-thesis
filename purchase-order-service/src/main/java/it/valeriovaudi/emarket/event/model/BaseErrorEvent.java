package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@ToString(callSuper = true)
public abstract class BaseErrorEvent extends AbstractDomainEvent {

    protected String message;
    protected String exceptionClassName;
}
