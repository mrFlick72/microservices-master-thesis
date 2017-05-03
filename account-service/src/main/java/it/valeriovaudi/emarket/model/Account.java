package it.valeriovaudi.emarket.model;

import it.valeriovaudi.emarket.validator.PasswordValidator;
import it.valeriovaudi.emarket.validator.UserNameValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Entity
public class Account {

    @Id
    @UserNameValidator
    private String userName;

    @PasswordValidator
    private String password;
    @Email
    private String mail;

    @NotEmpty
    @NotNull
    private String role;

    @Column(unique = true, length = 16)
    private String taxCode;

    @NotEmpty
    @NotNull
    private String firsrtName;

    @NotEmpty
    @NotNull
    private String lastName;

    @NotEmpty
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Embedded
    private Addrees addrees;

    @Embedded
    private TelephoneNumber telephoneNumber;
}
