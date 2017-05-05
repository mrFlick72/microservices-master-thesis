package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Map;

/**
 * Created by mrflick72 on 03/05/17.
 */


@Data
@Table
@ToString(callSuper = true)
public final class AccountValidationErrorEvent extends AbstractDomainEvent {

    private Map<String,String> validationError;
}
