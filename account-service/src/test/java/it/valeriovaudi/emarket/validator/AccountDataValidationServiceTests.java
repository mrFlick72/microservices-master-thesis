package it.valeriovaudi.emarket.validator;

import it.valeriovaudi.emarket.event.model.AccountValidationErrorEvent;
import it.valeriovaudi.emarket.exception.AccountValidationException;
import it.valeriovaudi.emarket.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
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
        account.setUserName("mrFlick72");
        account.setMail("mrFlick72@mail.com");
        try {
            accountDataValidationService.validate(UUID.randomUUID().toString(),account);
        } catch (AccountValidationException e){
            Map<String, String> validationError = ((AccountValidationErrorEvent) e.getEvent()).getValidationError();
            log.info("AccountValidationException  errors: " + validationError);
            Assert.assertNotNull(validationError);
            Assert.assertTrue(validationError.size() > 0);
            Assert.assertFalse(validationError.containsKey("userName"));
            Assert.assertFalse(validationError.containsKey("mail"));
        }
    }
}
