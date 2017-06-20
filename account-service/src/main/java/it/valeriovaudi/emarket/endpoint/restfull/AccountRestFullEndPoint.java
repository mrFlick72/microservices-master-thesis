package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.AccountHateoasFactory;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@RestController
@RequestMapping("/account")
public class AccountRestFullEndPoint {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountHateoasFactory accountHateoasFactory;

    @PostMapping
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createAccount(@RequestBody Account account){
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.created(MvcUriComponentsBuilder
                .fromMethodName(AccountRestFullEndPoint.class,"findAccount",savedAccount.getUserName())
                .build().toUri()).build();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findAccountList(){
        return ResponseEntity.ok(accountHateoasFactory.toResources(accountService.findAccountList()));
    }

    @GetMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findAccount(@PathVariable String userName){
        return ResponseEntity.ok(accountHateoasFactory.toResource(accountService.findAccount(userName)));
    }

    @PutMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity updateAccount(@PathVariable String userName, @RequestBody Account account){
        account.setUserName(userName);
        accountService.updateAccount(account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deleteAccount(@PathVariable String userName){
        accountService.deleteAccount(userName);
        return ResponseEntity.noContent().build();
    }
}
