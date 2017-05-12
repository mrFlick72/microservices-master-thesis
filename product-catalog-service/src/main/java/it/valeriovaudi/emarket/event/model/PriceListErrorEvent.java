package it.valeriovaudi.emarket.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by vvaudi on 10/05/17.
 */

@Data
@Table
@ToString
public class PriceListErrorEvent extends BaseErrorEvent {
    private String idPriceList;
    private String name;

    private EventTypeEnum type;
}
