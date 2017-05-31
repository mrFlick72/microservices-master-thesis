package it.valeriovaudi.emarket.anticorruptation.account;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
public class AccountToCustomerAnticorruptationLayerServiceHalJsonStrategy implements AnticCorruptationLayerStrategy<Customer> {

    @Override
    public Customer traslate(String body) {
        Customer customer = null;
        try {
            customer  = new Customer();
            ObjectNode node = (ObjectNode) new ObjectMapper().readTree(body);
            JsonNode embedded = node.get("_embedded");

            customer.setFirstName(embedded.get("firstName").asText());
            customer.setLastName(embedded.get("lastName").asText());
            customer.setTaxCode(embedded.get("taxCode").asText());
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return customer;
    }

}
