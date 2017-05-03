package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.factory.DomainEventFactoryTests;
import it.valeriovaudi.emarket.event.model.AccountCreationEvent;
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
    public void testAccountNotFoundEventRepository(){

    }

    @Test
    public void testAccountValidationErrorEventRepository(){

    }

    @Test
    public void testChangeAccountPasswordEventRepository(){

    }

    @Test
    public void testIdentityValidationErrorEventRepository(){

    }

    @Test
    public void testRemoveAccountErrorEventRepository(){

    }

    @Test
    public void testRemoveAccountEventRepository(){

    }

    @Test
    public void testSaveAccountErrorEventRepository(){
    }
}
