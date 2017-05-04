package it.valeriovaudi.emarket.validator;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by mrflick72 on 04/05/17.
 */

@Aspect
@Component
public class AccountDataValidationErrorEventPubblisherAspect {

    @AfterThrowing(throwing = "")
    public void pubblishErrorEvent(){

    }
}
