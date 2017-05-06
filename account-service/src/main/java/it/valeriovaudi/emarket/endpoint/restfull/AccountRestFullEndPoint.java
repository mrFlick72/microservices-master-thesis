package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.hatoas.AccountHatoasFactory;
import it.valeriovaudi.emarket.model.Account;
import it.valeriovaudi.emarket.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private AccountHatoasFactory accountHatoasFactory;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody Account account){
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.created(MvcUriComponentsBuilder
                .fromMethodName(AccountRestFullEndPoint.class,"findAccount",savedAccount.getUserName())
                .build().toUri()).build();
    }

    @GetMapping
    public ResponseEntity findAccountList(){
        return ResponseEntity.ok(accountHatoasFactory.toResources(accountService.findAccountList()));
    }

    @GetMapping("/{userName}")
    public ResponseEntity findAccount(@PathVariable String userName){
        return ResponseEntity.ok(accountHatoasFactory.toResource(accountService.findAccount(userName)));
    }

    @PutMapping("/{userName}")
    public ResponseEntity updateAccount(@PathVariable String userName, @RequestBody Account account){
        account.setUserName(userName);
        accountService.updateAccount(account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity deleteAccount(@PathVariable String userName){
        accountService.deleteAccount(userName);
        return ResponseEntity.noContent().build();
    }

}
