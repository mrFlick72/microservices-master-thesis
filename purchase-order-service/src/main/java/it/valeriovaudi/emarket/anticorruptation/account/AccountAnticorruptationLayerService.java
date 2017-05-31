package it.valeriovaudi.emarket.anticorruptation.account;

import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.Customer;
import it.valeriovaudi.emarket.model.CustomerContact;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by mrflick72 on 30/05/17.
 */


@Service
public class AccountAnticorruptationLayerService {

    private Map<String, AnticCorruptationLayerStrategy> customerAntiCorruptationRegistry;
    private Map<String, AnticCorruptationLayerStrategy> customerContactAnticorruptationRegistry;

    @PostConstruct
    public void init(){
        customerAntiCorruptationRegistry = new HashMap<>();
        customerAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, new AccountToCustomerAnticorruptationLayerServiceHalJsonStrategy());

        customerContactAnticorruptationRegistry = new HashMap<>();
        customerContactAnticorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, new AccountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy());
    }

    public Customer newCustomer(String customer, String mediaType){
        return (Customer) Optional.ofNullable(customerAntiCorruptationRegistry.get(mediaType))
                .map(anticCorruptationLayerStrategy -> anticCorruptationLayerStrategy.traslate(customer))
        .orElse(null);
    }

    public CustomerContact newCustomerContact(String gustomerContact, String mediaType){
        return (CustomerContact) Optional.ofNullable(customerContactAnticorruptationRegistry.get(mediaType))
                .map(anticCorruptationLayerStrategy -> anticCorruptationLayerStrategy.traslate(gustomerContact))
                .orElse(null);    }
}
