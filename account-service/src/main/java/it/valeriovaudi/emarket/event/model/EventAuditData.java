package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@UserDefinedType("eventAuditData")
public class EventAuditData {

    private String correlationId;
    private String userName;
    private Date timeStamp;
}
