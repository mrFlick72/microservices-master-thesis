package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Entity
public class Account {

    private String userName;
    private String password;
    private String mail;
    private String role;

    private String taxCode;
    private String firsrtName;
    private String lastName;
    private Date birthDate;

    private Addrees addrees;
    private TelephoneNumber telephoneNumber;
}
