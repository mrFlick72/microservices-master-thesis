package it.valeriovaudi.emarket.anticorruptation.account;

import it.valeriovaudi.emarket.anticorruptation.AbstractAntiCorruptationLayerService;
import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.Customer;
import it.valeriovaudi.emarket.model.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountAnticorruptationLayerService extends AbstractAntiCorruptationLayerService {

    private Map<String, AnticCorruptationLayerStrategy> customerAntiCorruptationRegistry;
    private Map<String, AnticCorruptationLayerStrategy> customerContactAnticorruptationRegistry;

    @Autowired
    private AccountToCustomerAnticorruptationLayerServiceHalJsonStrategy accountToCustomerAnticorruptationLayerServiceHalJsonStrategy;

    @Autowired
    private AccountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy accountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy;

    @PostConstruct
    public void init(){
        customerAntiCorruptationRegistry = new HashMap<>();
        customerAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, accountToCustomerAnticorruptationLayerServiceHalJsonStrategy);
        customerAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE, accountToCustomerAnticorruptationLayerServiceHalJsonStrategy);

        customerContactAnticorruptationRegistry = new HashMap<>();
        customerContactAnticorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, accountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy);
        customerContactAnticorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE, accountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy);
    }

    public Customer newCustomer(String customer, String mediaType){
        return (Customer) Optional.ofNullable(customerAntiCorruptationRegistry.get(mediaType))
                .map(antiCorruptationLayerStrategy -> antiCorruptationLayerStrategy.traslate(customer))
        .orElse(null);
    }

    public CustomerContact newCustomerContact(String gustomerContact, String mediaType){
        return (CustomerContact) Optional.ofNullable(customerContactAnticorruptationRegistry.get(mediaType))
                .map(antiCorruptationLayerStrategy -> antiCorruptationLayerStrategy.traslate(gustomerContact))
                .orElse(null);
    }
}
