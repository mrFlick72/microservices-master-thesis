package it.valeriovaudi.emarket.identity;

import it.valeriovaudi.emarket.exception.IdentityValidationException;
import it.valeriovaudi.emarket.model.Account;

/**
 * Created by mrflick72 on 04/05/17.
 */

// todo write after srping security integration
public interface IdentityIntegrityService {

    void validate(String correlationId, Account account) throws IdentityValidationException;

}
