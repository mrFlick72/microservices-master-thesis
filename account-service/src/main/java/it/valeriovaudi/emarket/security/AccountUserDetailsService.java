package it.valeriovaudi.emarket.security;

import it.valeriovaudi.emarket.repository.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by vvaudi on 06/05/17.
 */

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return Optional.ofNullable(accountRepository.findOne(userName))
                .map((account) -> new User(account.getUserName(), account.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(account.getRole()))))
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}