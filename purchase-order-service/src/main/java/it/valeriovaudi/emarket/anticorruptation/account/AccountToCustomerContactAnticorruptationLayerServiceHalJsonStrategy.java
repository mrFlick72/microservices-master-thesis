package it.valeriovaudi.emarket.anticorruptation.account;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.CustomerContact;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
public class AccountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy implements AnticCorruptationLayerStrategy<CustomerContact> {

    @Override
    public CustomerContact traslate(String body) {
        CustomerContact customerContact = null;
        try {
            customerContact  = new CustomerContact();
            ObjectNode node = (ObjectNode) new ObjectMapper().readTree(body);
            JsonNode embedded = node.get("_embedded");

            customerContact.setTelephone(String.format("%s %s%s",
                    embedded.get("countryPrefix").asText(),
                    embedded.get("prefix").asText(),
                    embedded.get("telephone").asText()));

            customerContact.setMail(embedded.get("mail").asText());
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return customerContact;
    }


}
