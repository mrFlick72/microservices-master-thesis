package it.valeriovaudi.emarket.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@Document
public class PurchaseOrder implements Serializable {

    @Id
    public String orderNumber;
    public Date orderDate;


    @Version
    private Long version;

    private PurchaseOrderStatusEnum purchaseOrderStatusEnum;

    private AuditData auditData;
    private Customer customer;
    private CustomerContact customerContact;
    private List<Goods> goodsList;
    private Shipment shipment;
    private Delivery delivery;
}
