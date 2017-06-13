package it.valeriovaudi.emarket.security;

import it.valeriovaudi.emarket.integration.LogInRequestGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by vvaudi on 06/05/17.
 */

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private LogInRequestGateway logInRequestGateway;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return logInRequestGateway.getPrincipleByUSerName(userName);
        /*Optional.ofNullable(accountRepository.findOne(userName))
                .map((account) -> new User(account.getUserName(), account.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(account.getRole()))))
                .orElseThrow(() -> new UsernameNotFoundException(""));*/
    }
}