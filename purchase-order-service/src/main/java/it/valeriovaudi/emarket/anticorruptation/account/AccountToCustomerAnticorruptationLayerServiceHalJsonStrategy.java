package it.valeriovaudi.emarket.anticorruptation.account;


import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.Customer;

/**
 * Created by mrflick72 on 30/05/17.
 */

public class AccountToCustomerAnticorruptationLayerServiceHalJsonStrategy implements AnticCorruptationLayerStrategy<Customer> {

    @Override
    public Customer traslate(String body) {
        return null;
    }

}
