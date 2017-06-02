package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.*;
import java.util.List;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderService {

    PurchaseOrder findPurchaseOrder(String userName, String orderNumber);
    List<PurchaseOrder> findPurchaseOrderList();
    List<PurchaseOrder> findPurchaseOrderList(String userName);

    PurchaseOrder createPurchaseOrder();
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
    void deletePurchaseOrder(String orderNumber);
    PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum);

    PurchaseOrder withCustomer(String orderNumber, String userName, Customer customer);
    PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact);

    PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact);


    PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber, String priceListId, String goodsId, int quantity);
    PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber,String goodsId);

    PurchaseOrder withShipment(String orderNumber, Shipment shipment);
    PurchaseOrder withDelivery(String orderNumber, Delivery delivery);
}
