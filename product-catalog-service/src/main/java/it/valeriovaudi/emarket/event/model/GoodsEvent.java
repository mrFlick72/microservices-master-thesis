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
public class GoodsEvent extends AbstractDomainEvent {

    private String idGoods;
    private String name;
    private String barCode;
    private String category;

    private EventTypeEnum type;
}
