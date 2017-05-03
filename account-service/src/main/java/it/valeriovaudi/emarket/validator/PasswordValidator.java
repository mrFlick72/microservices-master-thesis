package it.valeriovaudi.emarket.validator;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Created by mrflick72 on 03/05/17.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotNull
@NotEmpty
public @interface PasswordValidator {

}
