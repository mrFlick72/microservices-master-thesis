package it.valeriovaudi.emarket.endpoint.response;

import it.valeriovaudi.emarket.model.Customer;
import it.valeriovaudi.emarket.model.CustomerContact;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by mrflick72 on 13/06/17.
 */

@Data
@Builder
public class CustomerDataResponseDTO implements Serializable {
    private Customer customer;
    private CustomerContact customerContact;
}
