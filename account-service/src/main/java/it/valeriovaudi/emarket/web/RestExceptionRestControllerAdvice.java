package it.valeriovaudi.emarket.web;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import it.valeriovaudi.emarket.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by mrflick72 on 05/05/17.
 */

@RestControllerAdvice
public class RestExceptionRestControllerAdvice {

    @ExceptionHandler(value = {AbstractAccountException.class, AccountNotFoundException.class,
            AccountValidationException.class , IdentityValidationException.class,
            RemoveAccountException.class, SaveAccountException.class})
    public AbstractDomainEvent handleBadRequest(Exception ex) {
        AbstractDomainEvent event =  ((AbstractAccountException) ex).getEvent();
        return event;
    }

}
