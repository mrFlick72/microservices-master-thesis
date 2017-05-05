package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.repository.AccountRepository;
import it.valeriovaudi.emarket.validator.AccountDataValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Service
//@Cacheable
//@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDataValidationService accountDataValidationService;
    private final EventDomainPubblishService eventDomainPubblishService;
    private final AccountRepository accountRepository;
    private final DomainEventFactory domainEventFactory;

    public AccountServiceImpl(AccountDataValidationService accountDataValidationService,
                              EventDomainPubblishService eventDomainPubblishService,
                              AccountRepository accountRepository,
                              DomainEventFactory domainEventFactory) {
        this.accountDataValidationService = accountDataValidationService;
        this.eventDomainPubblishService = eventDomainPubblishService;
        this.accountRepository = accountRepository;
        this.domainEventFactory = domainEventFactory;
    }

    @Override
    public Account createAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validate(correlationId, account);
        // save account
        Account save = accountRepository.save(account);
        // fire save account event
        eventDomainPubblishService.publishAccountCreationEvent(correlationId, save.getUserName());
        return save;
    }

    @Override
    public List<Account> findAccountList(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public List<Account> findAccountList() {
        return null;
    }

    @Override
    public Account findAccount(String userName) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public void deleteAccount(String userName) {

    }
}
