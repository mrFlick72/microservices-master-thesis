package it.valeriovaudi.emarket.model;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by vvaudi on 02/07/17.
 */

@Data
public class AccountDTO {

    private String userName;
    private String password;
    private String mail;

    @Size(max = 16, min = 16)
    private String taxCode;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private AddressDTO address;

    private TelephoneNumberDTO telephoneNumber;
}
