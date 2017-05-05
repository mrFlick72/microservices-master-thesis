package it.valeriovaudi.emarket.event.factory;

import com.google.common.collect.Maps;
import it.valeriovaudi.emarket.event.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by vvaudi on 03/05/17.
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DomainEventFactoryTests {

    @Autowired
    private DomainEventFactory domainEventFactory;


    public static String correlationId = UUID.randomUUID().toString();
    public static String userName = "valval";
    public static String message = "test's message";
    public static Class exceptionClassName = Exception.class;
    public static String cause = "test's cause";
    public static Exception exception = new Exception(message, new Exception(cause));

    public static Map<String,String> errors = Stream.of("prop1","prop2","prop3","prop4")
            .map(s -> {
                HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
                objectObjectHashMap.put(s,"error");
                return objectObjectHashMap;})
            .reduce((stringStringHashMap, stringStringHashMap2) -> {
                stringStringHashMap.putAll(stringStringHashMap2);
                return stringStringHashMap;
            }).orElse(Maps.newHashMap());

    @Test
    public void testNewAccountCreationEvent(){
        AccountCreationEvent event = domainEventFactory.newAccountCreationEvent(correlationId,userName);
        log.info("AccountCreationEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("AccountCreationEvent.correlationId: " + correlationId);
        log.info("AccountCreationEvent.userName: " + userName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());
    }

    @Test
    public void testNewAccountNotFoundEvent(){
        AccountNotFoundEvent event = domainEventFactory.newAccountNotFoundEvent(correlationId,userName);
        log.info("testNewAccountNotFoundEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("AccountNotFoundEvent.correlationId: " + correlationId);
        log.info("AccountNotFoundEvent.userName: " + userName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());
    }

    @Test
    public void newAccountValidationErrorEvent(){
        AccountValidationErrorEvent event = domainEventFactory.newAccountValidationErrorEvent(correlationId,errors);
        log.info("newAccountValidationErrorEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("AccountValidationErrorEvent.correlationId: " + correlationId);
        log.info("AccountValidationErrorEvent.userName: " + userName);
        log.info("AccountValidationErrorEvent.errors: " + errors);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertSame(errors,event.getValidationError());

        Assert.assertNotNull(event.getValidationError());
    }

    @Test
    public void newChangeAccountPasswordEvent(){
        ChangeAccountPasswordEvent event = domainEventFactory.newChangeAccountPasswordEvent(correlationId,userName);
        log.info("newChangeAccountPasswordEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("ChangeAccountPasswordEvent.correlationId: " + correlationId);
        log.info("ChangeAccountPasswordEvent.userName: " + userName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());

    }

    @Test
    public void newIdentityValidationErrorEvent(){
        IdentityValidationErrorEvent event = domainEventFactory.newIdentityValidationErrorEvent(correlationId,userName,message);
        log.info("newIdentityValidationErrorEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("IdentityValidationErrorEvent.correlationId: " + correlationId);
        log.info("IdentityValidationErrorEvent.userName: " + userName);
        log.info("IdentityValidationErrorEvent.message: " + message);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());
        Assert.assertEquals(message,event.getMessage());
    }

    @Test
    public void newRemoveAccountErrorEvent(){
        RemoveAccountErrorEvent event = domainEventFactory.newRemoveAccountErrorEvent(correlationId,userName,message,exceptionClassName);
        log.info("newRemoveAccountErrorEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("RemoveAccountErrorEvent.correlationId: " + correlationId);
        log.info("RemoveAccountErrorEvent.userName: " + userName);
        log.info("RemoveAccountErrorEvent.message: " + message);
        log.info("RemoveAccountErrorEvent.exceptionClassName: " + exceptionClassName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());
        Assert.assertEquals(message,event.getMessage());
        Assert.assertEquals(exceptionClassName.getName(),event.getExceptionClassName());
    }

    @Test
    public void newRemoveAccountEvent(){
        RemoveAccountEvent event = domainEventFactory.newRemoveAccountEvent(correlationId,userName);
        log.info("newRemoveAccountEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("RemoveAccountEvent.correlationId: " + correlationId);
        log.info("RemoveAccountEvent.userName: " + userName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());

    }

    @Test
    public void newSaveAccountErrorEvent(){
        SaveAccountErrorEvent event = domainEventFactory.newSaveAccountErrorEvent(correlationId,userName,message,exceptionClassName);
        log.info("newRemoveAccountErrorEvent: " + event);
        Assert.assertNotNull(event);
        log.info("AccountNotFoundEvent.id: " + event.getId());
        log.info("SaveAccountErrorEvent.correlationId: " + correlationId);
        log.info("SaveAccountErrorEvent.userName: " + userName);
        log.info("SaveAccountErrorEvent.cause: " + cause);
        log.info("SaveAccountErrorEvent.message: " + message);
        log.info("SaveAccountErrorEvent.exceptionClassName: " + exceptionClassName);
        Assert.assertNotNull(event.getId());
        Assert.assertNotNull(event.getAuditData());
        Assert.assertNotNull(event.getAuditData().getCorrelationId());
        Assert.assertNotNull(event.getAuditData().getUserName());
        Assert.assertNotNull(event.getAuditData().getTimeStamp());
        Assert.assertEquals(correlationId,event.getAuditData().getCorrelationId());
        Assert.assertEquals(userName,event.getUserName());
        Assert.assertEquals(message,event.getMessage());
        Assert.assertEquals(exceptionClassName.getName(),event.getExceptionClassName());
    }
}
