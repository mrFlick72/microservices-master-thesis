package it.valeriovaudi.emarket.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Entity
public class Account {

    @Id
    private String userName;

    private String password;

    private String mail;

    private String role;

    @Column(length = 16)
    private String taxCode;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Embedded
    private Address address;

    @Embedded
    private TelephoneNumber telephoneNumber;
}