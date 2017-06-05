package it.valeriovaudi.emarket.integration;

import it.valeriovaudi.emarket.anticorruptation.account.AccountAnticorruptationLayerService;
import it.valeriovaudi.emarket.model.Customer;
import it.valeriovaudi.emarket.model.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Service
public class AccountIntegrationService {

    @Autowired
    private AccountAnticorruptationLayerService accountAnticorruptationLayerService;

    @Autowired
    private OAuth2RestTemplate accountIntegrationServiceRestTemplate;

    @Value("external-service.base-uri-schema.account")
    private String accountServiceUriSchema;

    public Customer getCustomerFormAccountData(String userName){
        ResponseEntity<String> serviceCall = serviceCall(userName);
        return accountAnticorruptationLayerService.newCustomer(serviceCall.getBody(),
                serviceCall.getHeaders().getContentType().getType());
    }

    public CustomerContact getCustomerContactFormAccountData(String userName){
        ResponseEntity<String> serviceCall = serviceCall(userName);
        return accountAnticorruptationLayerService.newCustomerContact(serviceCall.getBody(),
                serviceCall.getHeaders().getContentType().getType());
    }

    private ResponseEntity<String> serviceCall(String userName){
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/%s", accountServiceUriSchema, userName))
                .build().toUri();
        RequestEntity<Void> build = RequestEntity.get(uri).build();
        return accountIntegrationServiceRestTemplate.exchange(build, String.class);
    }
}
