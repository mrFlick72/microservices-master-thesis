package it.valeriovaudi.emarket.event.service;

import it.valeriovaudi.emarket.event.config.EventMessageChannels;
import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.model.*;
import it.valeriovaudi.emarket.event.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by mrflick72 on 04/05/17.
 */

@Data
@Service
public class EventDomainPubblishService {

    @Autowired
    private DomainEventFactory domainEventFactory;
    @Autowired
    private AccountCreationEventRepository accountCreationEventRepository;
    @Autowired
    private AccountNotFoundEventRepository accountNotFoundEventRepository;
    @Autowired
    private AccountValidationErrorEventRepository accountValidationErrorEventRepository;
    @Autowired
    private ChangeAccountPasswordEventRepository changeAccountPasswordEventRepository;
    @Autowired
    private IdentityValidationErrorEventRepository identityValidationErrorEventRepository;
    @Autowired
    private RemoveAccountErrorEventRepository removeAccountErrorEventRepository;
    @Autowired
    private RemoveAccountEventRepository removeAccountEventRepository;
    @Autowired
    private SaveAccountErrorEventRepository saveAccountErrorEventRepository;
    @Autowired
    private SubscribableChannel accountEventOutboundChannel;

    public AccountCreationEvent publishAccountCreationEvent(String correlationId, String userName){
        AccountCreationEvent event = domainEventFactory.newAccountCreationEvent(correlationId, userName);
        accountCreationEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public AccountNotFoundEvent publishAccountNotFoundEvent(String correlationId, String userName){
        AccountNotFoundEvent event = domainEventFactory.newAccountNotFoundEvent(correlationId, userName);
        accountNotFoundEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public AccountValidationErrorEvent publishAccountValidationErrorEvent(String correlationId, Map<String,String> errors){
        AccountValidationErrorEvent event = domainEventFactory.newAccountValidationErrorEvent(correlationId, errors);
        accountValidationErrorEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public ChangeAccountPasswordEvent publishChangeAccountPasswordEvent(String correlationId, String userName){
        ChangeAccountPasswordEvent event = domainEventFactory.newChangeAccountPasswordEvent(correlationId, userName);
        changeAccountPasswordEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public IdentityValidationErrorEvent publishIdentityValidationErrorEvent(String correlationId, String userName, String message){
        IdentityValidationErrorEvent event = domainEventFactory.newIdentityValidationErrorEvent(correlationId, userName, message);
        identityValidationErrorEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public RemoveAccountErrorEvent publishRemoveAccountErrorEvent(String correlationId, String userName,
                                               String message, Class exceptionClassName){

        RemoveAccountErrorEvent event = domainEventFactory.newRemoveAccountErrorEvent(correlationId, userName, message,exceptionClassName);
        removeAccountErrorEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public RemoveAccountEvent publishRemoveAccountEvent(String correlationId, String userName){
        RemoveAccountEvent event = domainEventFactory.newRemoveAccountEvent(correlationId, userName);
        removeAccountEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public SaveAccountErrorEvent publishSaveAccountErrorEvent(String correlationId,String userName,
                                             String message, Class exceptionClassName){

        SaveAccountErrorEvent event = domainEventFactory.newSaveAccountErrorEvent(correlationId, userName, message, exceptionClassName);
        saveAccountErrorEventRepository.save(event);
        accountEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }
}