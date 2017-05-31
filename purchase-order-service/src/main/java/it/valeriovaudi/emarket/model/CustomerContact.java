package it.valeriovaudi.emarket.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
public class CustomerContact implements Serializable {

    private String telephone;
    private String mail;
}
