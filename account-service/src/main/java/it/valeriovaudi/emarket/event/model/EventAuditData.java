package it.valeriovaudi.emarket.event.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
public class EventAuditData {

    private String correlationId;
    private String userName;
    private Date timeStamp;
}
