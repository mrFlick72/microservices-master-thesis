package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Table
@ToString(callSuper = true)
public final class RemoveAccountEvent  extends AbstractDomainEvent {
    private String userName;

}
