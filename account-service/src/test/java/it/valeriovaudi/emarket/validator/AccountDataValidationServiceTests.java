package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.event.model.AccountValidationErrorEvent;
import it.valeriovaudi.emarket.exception.AccountValidationException;
import it.valeriovaudi.emarket.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by vvaudi on 04/05/17.
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountDataValidationServiceTests {

    @Autowired
    private AccountDataValidationService accountDataValidationService;

    @Test
    public void testAccountDataValidationService(){
        Account account = new Account();
//        account.setMail("vvaudi@mail.com");
        try {
            accountDataValidationService.validate(UUID.randomUUID().toString(),account);
        } catch (AccountValidationException e){
            log.info("AccountValidationException  errors: " + ((AccountValidationErrorEvent) e.getEvent()).getValidationError());
        }

    }
}
