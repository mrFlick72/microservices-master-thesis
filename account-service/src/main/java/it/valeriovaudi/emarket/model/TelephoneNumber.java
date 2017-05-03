package it.valeriovaudi.emarket.model;

import lombok.Data;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
public class TelephoneNumber {
    private String countryPrefix;
    private String prefix;
    private String telephone;
}
