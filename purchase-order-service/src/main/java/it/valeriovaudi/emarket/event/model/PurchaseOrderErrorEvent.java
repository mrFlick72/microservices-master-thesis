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
public class PurchaseOrderErrorEvent extends BaseErrorEvent {

    private String idPurchaseOrder;
    private String idProductCatalog;
    private String idGoodsInPurchaseOrder;
    private String customerUserName;

    private EventTypeEnum type;
}
