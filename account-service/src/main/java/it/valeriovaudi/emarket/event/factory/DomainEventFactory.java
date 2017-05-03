package it.valeriovaudi.emarket.event.factory;

import it.valeriovaudi.emarket.event.model.*;
import org.springframework.stereotype.Component;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Component
public class DomainEventFactory {

    public AccountCreationEvent newAccountCreationEvent(){
        AccountCreationEvent event = new AccountCreationEvent();
        return event;
    }

    public AccountNotFoundEvent newAccountNotFoundEvent(){
        AccountNotFoundEvent event = new AccountNotFoundEvent();
        return event;
    }

    public AccountValidationErrorEvent newAccountValidationErrorEvent(){
        AccountValidationErrorEvent event = new AccountValidationErrorEvent();
        return event;
    }

    public ChangeAccountPasswordEvent newChangeAccountPasswordEvent(){
        ChangeAccountPasswordEvent event = new ChangeAccountPasswordEvent();
        return event;
    }

    public IdentityValidationErrorEvent newIdentityValidationErrorEvent(){
        IdentityValidationErrorEvent event = new IdentityValidationErrorEvent();
        return event;
    }

    public RemoveAccountErrorEvent newRemoveAccountErrorEvent(){
        RemoveAccountErrorEvent event = new RemoveAccountErrorEvent();
        return event;
    }

    public RemoveAccountEvent newRemoveAccountEvent(){
        RemoveAccountEvent event = new RemoveAccountEvent();
        return event;
    }

    public SaveAccountErrorEvent newSaveAccountErrorEvent(){
        SaveAccountErrorEvent event = new SaveAccountErrorEvent();
        return event;
    }

}
