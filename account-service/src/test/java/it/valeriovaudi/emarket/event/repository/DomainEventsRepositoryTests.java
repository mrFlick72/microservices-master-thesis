package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.model.AccountCreationEvent;
import it.valeriovaudi.emarket.event.model.SaveAccountErrorEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static it.valeriovaudi.emarket.event.factory.DomainEventFactoryTests.*;

/**
 * Created by vvaudi on 04/05/17.
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DomainEventsRepositoryTests {

    @Autowired
    private DomainEventFactory domainEventFactory;

    @Autowired
    private AccountCreationEventRepository accountCreationEventRepository;

    @Autowired
    private SaveAccountErrorEventRepository saveAccountErrorEventRepository;


    @Test
    public void testAccountCreationEventRepository(){
        AccountCreationEvent accountCreationEvent =
                domainEventFactory.newAccountCreationEvent(correlationId, userName);

        log.info("AccountCreationEvent: " + accountCreationEvent);
        Assert.assertNotNull(accountCreationEvent);
        log.info("AccountCreationEvent.id: " + accountCreationEvent.getId());
        Assert.assertNotNull(accountCreationEvent.getId());

        AccountCreationEvent save = accountCreationEventRepository.save(accountCreationEvent);
        log.info("saved AccountCreationEvent: " + save);
        Assert.assertNotNull(save);
        Assert.assertSame(accountCreationEvent.getId(),save.getId());

        accountCreationEventRepository.delete(save);
    }
    @Test
    public void testSaveAccountErrorEventRepository(){
        SaveAccountErrorEvent saveAccountErrorEvent =
                domainEventFactory.newSaveAccountErrorEvent(correlationId, userName,  message, exceptionClassName);

        log.info("SaveAccountErrorEvent: " + saveAccountErrorEvent);
         SaveAccountErrorEvent save = saveAccountErrorEventRepository.save(saveAccountErrorEvent);
        log.info("saved SaveAccountErrorEvent: " + save);
        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getAuditData());
        Assert.assertNotNull(save.getAuditData().getTimeStamp());
        Assert.assertNotNull(save.getId());
        Assert.assertEquals(correlationId, save.getAuditData().getCorrelationId());
        Assert.assertNotNull(save.getAuditData().getTimeStamp());
        log.info("SaveAccountErrorEvent.id: " + save.getId());
        log.info("SaveAccountErrorEvent.correlationId: " + save.getAuditData().getCorrelationId());
        log.info("SaveAccountErrorEvent.timestamp: " + save.getAuditData().getTimeStamp());
        log.info("SaveAccountErrorEvent.userName: " + save.getUserName());
        log.info("SaveAccountErrorEvent.cause: " + save.getClass());
        log.info("SaveAccountErrorEvent.message: " + save.getMessage());
        log.info("SaveAccountErrorEvent.exceptionClassName: " + save.getExceptionClassName());

        Assert.assertSame(saveAccountErrorEvent.getId(),save.getId());
        Assert.assertSame(saveAccountErrorEvent.getAuditData().getCorrelationId(),save.getAuditData().getCorrelationId());
        Assert.assertSame(saveAccountErrorEvent.getAuditData().getTimeStamp(),save.getAuditData().getTimeStamp());
        Assert.assertSame(saveAccountErrorEvent.getUserName(),save.getUserName());
        Assert.assertSame(saveAccountErrorEvent.getMessage(),save.getMessage());
        Assert.assertSame(saveAccountErrorEvent.getExceptionClassName(),save.getExceptionClassName());

        saveAccountErrorEventRepository.delete(save);

    }
}
