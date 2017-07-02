package it.valeriovaudi.emarket.controller;

import it.valeriovaudi.emarket.model.AccountDTO;
import it.valeriovaudi.emarket.model.AddressDTO;
import it.valeriovaudi.emarket.model.TelephoneNumberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by vvaudi on 02/07/17.
 */

//@Slf4j
//@Controller
public class SingUpController {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @GetMapping("/public/signup")
    public String singUp(Model model){
        if(!model.containsAttribute("account")){
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAddress(new AddressDTO());
            accountDTO.setTelephoneNumber(new TelephoneNumberDTO());
            model.addAttribute("account", accountDTO);
        }

        return "public/singup";
    }

    @PostMapping("/public/signup")
    public String doSingUp(@Valid @ModelAttribute("account") AccountDTO account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "public/singup";
        } else {
            return "private/index";
        }
    }
}
