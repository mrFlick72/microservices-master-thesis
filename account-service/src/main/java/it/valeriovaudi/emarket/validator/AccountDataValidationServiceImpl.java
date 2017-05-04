package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.exception.AccountValidationException;
import it.valeriovaudi.emarket.model.Account;

/**
 * Created by mrflick72 on 04/05/17.
 */
public class AccountDataValidationServiceImpl implements AccountDataValidationService {

    @Override
    public void validate(Account account) throws AccountValidationException {

        String userName = account.getUserName();
        String password = account.getPassword();
        String taxCode = account.getTaxCode();
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
    }
}
