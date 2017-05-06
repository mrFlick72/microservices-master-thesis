package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Account;

import java.util.List;

/**
 * Created by vvaudi on 04/05/17.
 */
public interface AccountService {

    Account createAccount(Account account);

    List<Account> findAccountList();

    Account findAccount(String userName);

    Account updateAccount(Account account);

    void deleteAccount(String userName);
}
