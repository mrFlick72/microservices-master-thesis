package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
public abstract class AbstractDomainEvent {

    @Id
    protected UUID id;
    protected EventAuditData auditData;
}
