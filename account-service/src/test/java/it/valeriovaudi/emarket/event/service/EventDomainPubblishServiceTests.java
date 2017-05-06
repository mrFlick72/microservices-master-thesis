package it.valeriovaudi.emarket.event.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.test.context.junit4.SpringRunner;

import static it.valeriovaudi.emarket.event.factory.DomainEventFactoryTests.*;

/**
 * Created by mrflick72 on 04/05/17.
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventDomainPubblishServiceTests {

    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    private SubscribableChannel accountEventOutboundChannel;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void testPublishAccountCreationEvent() throws InterruptedException {
        eventDomainPubblishService.publishAccountCreationEvent(correlationId, userName);
        verify();
    }

    @Test
    public void testPublishAccountNotFoundEvent(){
        eventDomainPubblishService.publishAccountNotFoundEvent(correlationId, userName);
        verify();
    }

    @Test
    public void testPublishAccountValidationErrorEvent(){
        eventDomainPubblishService.publishAccountValidationErrorEvent(correlationId, errors);
        verify();
    }

    @Test
    public void testPublishChangeAccountPasswordEvent(){
        eventDomainPubblishService.publishChangeAccountPasswordEvent(correlationId, userName);
        verify();
    }

    @Test
    public void testPublishIdentityValidationErrorEvent(){
        eventDomainPubblishService.publishIdentityValidationErrorEvent(correlationId, userName,message);
        verify();
    }

    @Test
    public void testPublishRemoveAccountErrorEvent(){
        eventDomainPubblishService.publishRemoveAccountErrorEvent(correlationId, userName, message, exceptionClassName);
        verify();
    }

    @Test
    public void testPublishRemoveAccountEvent(){
        eventDomainPubblishService.publishRemoveAccountEvent(correlationId, userName);
        verify();
    }

    @Test
    public void testPblishSaveAccountErrorEvent(){
        eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, userName,  message, exceptionClassName);
        verify();
    }

    private void verify(){
        Message received = messageCollector.forChannel(accountEventOutboundChannel).poll();
        log.info("message: " + received);
        Assert.assertNotNull(received);
    }
}
