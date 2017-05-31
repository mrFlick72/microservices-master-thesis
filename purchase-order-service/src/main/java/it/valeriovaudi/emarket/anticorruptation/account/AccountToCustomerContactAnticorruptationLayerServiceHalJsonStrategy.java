package it.valeriovaudi.emarket.anticorruptation.account;


import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.CustomerContact;

/**
 * Created by mrflick72 on 30/05/17.
 */

public class AccountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy implements AnticCorruptationLayerStrategy<CustomerContact> {

    @Override
    public CustomerContact traslate(String body) {
        return null;
    }


}
