package it.valeriovaudi.emarket.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
public class Delivery implements Serializable {

    private String personDeliveryName;
    private Date deliveryDate;

}
