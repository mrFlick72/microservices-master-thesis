package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Embeddable
public class Address {
    private String street;
    private String streenNumber;
    private String zip;
    private String country;
    private String region;
    private String city;
}