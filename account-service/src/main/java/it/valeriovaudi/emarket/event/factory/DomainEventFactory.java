package it.valeriovaudi.emarket.event.factory;

import com.datastax.driver.core.utils.UUIDs;
import it.valeriovaudi.emarket.event.model.*;
import it.valeriovaudi.emarket.security.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Component
public class DomainEventFactory {

    @Autowired
    private SecurityUtils securityUtils;

    public AccountCreationEvent newAccountCreationEvent(String correlationId, String userName){
        AccountCreationEvent event = new AccountCreationEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public AccountNotFoundEvent newAccountNotFoundEvent(String correlationId, String userName){
        AccountNotFoundEvent event = new AccountNotFoundEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public AccountValidationErrorEvent newAccountValidationErrorEvent(String correlationId, Map<String,String> errors){
        AccountValidationErrorEvent event = new AccountValidationErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setValidationError(errors);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public ChangeAccountPasswordEvent newChangeAccountPasswordEvent(String correlationId, String userName){
        ChangeAccountPasswordEvent event = new ChangeAccountPasswordEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public IdentityValidationErrorEvent newIdentityValidationErrorEvent(String correlationId, String userName, String message){
        IdentityValidationErrorEvent event = new IdentityValidationErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setMessage(message);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public RemoveAccountErrorEvent newRemoveAccountErrorEvent(String correlationId, String userName,
                                                              String message, Class exceptionClassName){
        RemoveAccountErrorEvent event = new RemoveAccountErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setMessage(message);
        event.setExceptionClassName(exceptionClassName.getName());
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public RemoveAccountEvent newRemoveAccountEvent(String correlationId, String userName){
        RemoveAccountEvent event = new RemoveAccountEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public SaveAccountErrorEvent newSaveAccountErrorEvent(String correlationId,String userName,
                                                          String message, Class exceptionClassName){
        SaveAccountErrorEvent event = new SaveAccountErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setUserName(userName);
        event.setMessage(message);
        event.setExceptionClassName(exceptionClassName.getName());
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }


    private EventAuditData newEventAuditData(String correlationId){
        EventAuditData eventAuditData = new EventAuditData();

        eventAuditData.setCorrelationId(correlationId);
        eventAuditData.setUserName(securityUtils.getPrincipalUserName());
        eventAuditData.setTimeStamp(new Date());

        return eventAuditData;
    }
}
