package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.model.AccountNotFoundEvent;
import it.valeriovaudi.emarket.event.model.AccountValidationErrorEvent;
import it.valeriovaudi.emarket.event.model.SaveAccountErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.AccountNotFoundException;
import it.valeriovaudi.emarket.exception.RemoveAccountException;
import it.valeriovaudi.emarket.exception.SaveAccountException;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.repository.AccountRepository;
import it.valeriovaudi.emarket.validator.AccountDataValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDataValidationService accountDataValidationService;
    private final DomainEventFactory domainEventFactory;
    private final EventDomainPubblishService eventDomainPubblishService;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountDataValidationService accountDataValidationService,
                              DomainEventFactory domainEventFactory,
                              EventDomainPubblishService eventDomainPubblishService,
                              AccountRepository accountRepository) {
        this.accountDataValidationService = accountDataValidationService;
        this.domainEventFactory = domainEventFactory;
        this.eventDomainPubblishService = eventDomainPubblishService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validate(correlationId, account);
        // save account
        Account save = doSaveAccountData(correlationId, account);
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
        String correlationId = UUID.randomUUID().toString();

        // data validation
        accountDataValidationService.validate(correlationId, account);

        // check the presence of an account in the database
        doCheckAccountExist(correlationId, account.getUserName());

        // save account
        Account save = doSaveAccountData(correlationId, account);

        if(!account.getPassword().equals(save.getPassword())){
            // fire change password account event
            eventDomainPubblishService.publishChangeAccountPasswordEvent(correlationId, save.getUserName());
        }
        // fire save account event
        return save;
    }

    @Override
    public void deleteAccount(String userName) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validateUserName(correlationId,userName);
        // delete account
        doDeleteAccount(correlationId,userName);
        // fire remove account event
        eventDomainPubblishService.publishRemoveAccountEvent(correlationId,userName);
    }

    private Account doSaveAccountData(String correlationId, Account account) throws SaveAccountException {
        Account accountAux;

        try{
            accountAux = accountRepository.save(account);
        } catch (Exception e){
            SaveAccountErrorEvent saveAccountErrorEvent = domainEventFactory.newSaveAccountErrorEvent(correlationId, account.getUserName(),
                    SaveAccountException.DEFAULT_MESSAGE, SaveAccountException.class);

            SaveAccountException saveAccountException = new SaveAccountException(saveAccountErrorEvent, SaveAccountException.DEFAULT_MESSAGE);
            eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, account.getUserName(),
                    SaveAccountException.DEFAULT_MESSAGE, SaveAccountException.class);

            throw saveAccountException;
        }

        return accountAux;
    }

    private Account doCheckAccountExist(String correlationId, String userName) throws AccountNotFoundException {
        Account accountAux;

        Function<String, AccountNotFoundException> f =(userNameAux) -> {
            AccountNotFoundEvent accountNotFoundEvent = domainEventFactory.newAccountNotFoundEvent(correlationId, userNameAux);
            AccountNotFoundException accountNotFoundException = new AccountNotFoundException(accountNotFoundEvent, AccountNotFoundException.DEFAULT_MESSAGE);
            eventDomainPubblishService.publishAccountNotFoundEvent(correlationId, userNameAux);
            return accountNotFoundException;
        };

        try{
            accountAux =  accountRepository.findOne(userName);
            if(accountAux == null){
                throw f.apply(userName);
            }
        } catch (Exception e){
            throw f.apply(userName);
        }

        return accountAux;
    }

    private void doDeleteAccount(String correlationId, String userName) throws RemoveAccountException, AccountNotFoundException {
        doCheckAccountExist(correlationId, userName);
        accountRepository.delete(userName);
    }

}
