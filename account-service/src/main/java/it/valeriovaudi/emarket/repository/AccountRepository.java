package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.Account;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mrflick72 on 03/05/17.
 */
public interface AccountRepository extends JpaRepository<Account,String> { }
