package it.valeriovaudi.emarket.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@ToString
public class Shipment implements Serializable {

    private String streetAddress;
    private String streetNumber;
    private String zip;
    private String shipmentDate;

}
