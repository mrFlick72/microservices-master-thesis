package it.valeriovaudi.emarket.integration;

import it.valeriovaudi.emarket.anticorruptation.account.AccountAnticorruptationLayerService;
import it.valeriovaudi.emarket.model.Customer;
import it.valeriovaudi.emarket.model.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Service
public class AccountIntegrationService {

    @Autowired
    private AccountAnticorruptationLayerService accountAnticorruptationLayerService;

    @Autowired
    private OAuth2RestTemplate accountIntegrationServiceRestTemplate;

    public Customer getCustomerFormAccountData(String userName){
        //....
        return null;
    }

    public CustomerContact getCustomerContactFormAccountData(String userName){
        //....
        return null;
    }

}
