package it.valeriovaudi.emarket.hatoas;

import it.valeriovaudi.emarket.endpoint.restfull.AccountRestFullEndPoint;
import it.valeriovaudi.emarket.model.Account;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by vvaudi on 06/05/17.
 */

@Component
public class AccountHatoasFactory{

    public Resource<Account> toResource(Account account) {
        Resource<Account> accountResource = new Resource<>(account);

        Link link = linkTo(AccountRestFullEndPoint.class).slash(account.getUserName())
                .withSelfRel();
        accountResource.add(link);
        return accountResource;
    }

    public Resources<Account> toResources(List<Account> accountList) {
        Resources<Account> accountResource = new Resources<>(accountList);

        UriTemplate uriTemplate = new UriTemplate(String.format("%s/{userName}",linkTo(AccountRestFullEndPoint.class).toString()));
        Link accountLink = new Link(uriTemplate, "account");
        accountResource.add(accountLink);
        return accountResource;
    }
}
