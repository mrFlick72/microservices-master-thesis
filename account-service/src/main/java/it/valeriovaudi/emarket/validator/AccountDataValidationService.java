package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.exception.AccountValidationException;
import it.valeriovaudi.emarket.model.Account;

/**
 * Created by mrflick72 on 04/05/17.
 */
public interface AccountDataValidationService {

    void validate(String correlationId, Account account) throws AccountValidationException;
    void validateUserName(String correlationId, String userName) throws AccountValidationException;
}
