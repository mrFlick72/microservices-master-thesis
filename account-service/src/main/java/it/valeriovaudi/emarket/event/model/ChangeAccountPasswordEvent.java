package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;


/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Table
public class ChangeAccountPasswordEvent extends AbstractDomainEvent {

    private String userName;

}
