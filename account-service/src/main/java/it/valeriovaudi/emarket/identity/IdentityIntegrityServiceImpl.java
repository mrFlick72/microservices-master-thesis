package it.valeriovaudi.emarket.identity;

import it.valeriovaudi.emarket.exception.IdentityValidationException;
import it.valeriovaudi.emarket.model.Account;
import org.springframework.stereotype.Component;

/**
 * Created by vvaudi on 04/05/17.
 */

@Component
public class IdentityIntegrityServiceImpl implements IdentityIntegrityService {
    @Override
    public void validate(String correlationId, Account account) throws IdentityValidationException {

    }
}
