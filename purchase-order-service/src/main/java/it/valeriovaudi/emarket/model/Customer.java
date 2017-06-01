package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@ToString
public class Customer implements Serializable {

    private String firstName;
    private String lastName;
    private String taxCode;
}
