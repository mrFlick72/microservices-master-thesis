package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.AccountNotFoundEvent;
import it.valeriovaudi.emarket.event.model.RemoveAccountErrorEvent;
import it.valeriovaudi.emarket.event.model.SaveAccountErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.AccountNotFoundException;
import it.valeriovaudi.emarket.exception.ConflictSaveAccountException;
import it.valeriovaudi.emarket.exception.RemoveAccountException;
import it.valeriovaudi.emarket.exception.SaveAccountException;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.repository.AccountRepository;
import it.valeriovaudi.emarket.validator.AccountDataValidationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Created by mrflick72 on 04/05/17.
 */
@Data
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDataValidationService accountDataValidationService;

    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validate(correlationId, account);
        // save account
        Account save = doSaveAccountData(correlationId, account, true);
        // fire save account event
        eventDomainPubblishService.publishAccountCreationEvent(correlationId, save.getUserName());
        return save;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAccountList() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccount(String userName) {
        doCheckAccountExist(UUID.randomUUID().toString(),userName);
        return accountRepository.findOne(userName);
    }

    @Override
    public Account updateAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();

        // data validation
        accountDataValidationService.validate(correlationId, account);

        // check the presence of an account in the database
        doCheckAccountExist(correlationId, account.getUserName());

        // save account
        Account save = doSaveAccountData(correlationId, account, false);

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
        doDeleteAccount(correlationId, userName);
        // fire remove account event
        eventDomainPubblishService.publishRemoveAccountEvent(correlationId,userName);
    }

    private Account doSaveAccountData(String correlationId, Account account, boolean checkDuplicate) {
        Account accountAux = account;
        ConflictSaveAccountException conflictSaveAccountException = null;
        SaveAccountErrorEvent saveAccountErrorEvent;
        try{
            if(checkDuplicate){
                Account one = accountRepository.findOne(account.getUserName());
                if(one == null){
                    accountAux = accountRepository.saveAndFlush(account);
                } else {
                    saveAccountErrorEvent = eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, account.getUserName(),
                            ConflictSaveAccountException.DEFAULT_MESSAGE, ConflictSaveAccountException.class);
                    conflictSaveAccountException = new ConflictSaveAccountException(saveAccountErrorEvent, ConflictSaveAccountException.DEFAULT_MESSAGE);
                }
            }else {
                accountAux = accountRepository.saveAndFlush(account);
            }
        } catch (Exception e){
             saveAccountErrorEvent = eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, account.getUserName(),
                     SaveAccountException.DEFAULT_MESSAGE, SaveAccountException.class);
            throw new SaveAccountException(saveAccountErrorEvent, SaveAccountException.DEFAULT_MESSAGE);
        }

        if(conflictSaveAccountException!= null){
            throw conflictSaveAccountException;
        }

        return accountAux;
    }

    private void doCheckAccountExist(String correlationId, String userName) {
        Account accountAux;

        Function<String, AccountNotFoundException> f = userNameAux -> {
            AccountNotFoundEvent accountNotFoundEvent = eventDomainPubblishService.publishAccountNotFoundEvent(correlationId, userNameAux);
            return new AccountNotFoundException(accountNotFoundEvent, AccountNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            accountAux =  accountRepository.findOne(userName);
            if(accountAux == null){
                throw f.apply(userName);
            }
        } catch (Exception e){
            throw f.apply(userName);
        }
    }

    private void doDeleteAccount(String correlationId, String userName) {
        doCheckAccountExist(correlationId, userName);
        try{
            accountRepository.delete(userName);
        } catch (Exception e){
            RemoveAccountErrorEvent removeAccountErrorEvent = eventDomainPubblishService.publishRemoveAccountErrorEvent(correlationId, userName,RemoveAccountException.DEFAULT_MESSAGE,RemoveAccountException.class);
            throw new RemoveAccountException(removeAccountErrorEvent, RemoveAccountException.DEFAULT_MESSAGE);
        }
    }
}
