package it.valeriovaudi.emarket.anticorruptation.account;


import com.fasterxml.jackson.databind.node.ObjectNode;
import it.valeriovaudi.emarket.anticorruptation.AbstractAnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.CustomerContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
@Component
public class AccountToCustomerContactAnticorruptationLayerServiceHalJsonStrategy extends AbstractAnticCorruptationLayerStrategy<CustomerContact> {

    @Override
    public CustomerContact traslate(String body) {
        CustomerContact customerContact = null;
        try {
            customerContact  = new CustomerContact();
            ObjectNode node = (ObjectNode) objectMapper.readTree(body);

            customerContact.setTelephone(String.format("%s %s%s",
                    node.get("countryPrefix").asText(),
                    node.get("prefix").asText(),
                    node.get("telephone").asText()));

            customerContact.setMail(node.get("mail").asText());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return customerContact;
    }


}
