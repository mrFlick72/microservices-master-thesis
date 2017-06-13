package it.valeriovaudi.emarket.restfull;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Slf4j
@RestController
@RequestMapping("/account")
public class UserRestFullEndPoint {

    @GetMapping("/userInfo")
    public Principal userInfo(Principal principal){
        log.info("userInfo" + principal);
        log.info("userInfo" + SecurityContextHolder.getContext().getAuthentication());
        return principal;
    }
}
