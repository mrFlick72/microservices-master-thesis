package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.validator.AccountDataValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Service
//@Cacheable
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDataValidationService accountDataValidationService;
    private final DomainEventFactory domainEventFactory;

    public AccountServiceImpl(AccountDataValidationService accountDataValidationService,
                              DomainEventFactory domainEventFactory) {
        this.accountDataValidationService = accountDataValidationService;
        this.domainEventFactory = domainEventFactory;
    }

    @Override
    @Validated
    public Account createAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validate(correlationId, account);
        // save account

        // fire save account event
        return null;
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
