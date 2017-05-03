package it.valeriovaudi.emarket.validator;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by mrflick72 on 03/05/17.
 */

@NotNull
@NotEmpty
public @interface UserNameValidator {
}
