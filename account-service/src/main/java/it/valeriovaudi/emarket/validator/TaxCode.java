package it.valeriovaudi.emarket.validator;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by mrflick72 on 03/05/17.
 */

@NotNull
@NotEmpty
@Size(min = 16, max = 16)
public @interface TaxCode {
}
