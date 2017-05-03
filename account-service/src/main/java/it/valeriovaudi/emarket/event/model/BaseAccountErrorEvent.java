package it.valeriovaudi.emarket.event.model;

import lombok.Data;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
public abstract class BaseAccountErrorEvent extends AbstractDomainEvent {

    protected String cause;
    protected String message;
    protected String exceptionClassName;
}
