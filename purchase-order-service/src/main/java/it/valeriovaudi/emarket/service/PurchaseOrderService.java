package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.*;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderService {

    PurchaseOrder createPurchaseOrder();
    PurchaseOrder deletePurchaseOrder(String orderNumber);
    PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum);

    PurchaseOrder withCustomer(String orderNumber, String userName, Customer customer);
    PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact);

    PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact);


    PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber,String goodsId);
    PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber,String goodsId);

    PurchaseOrder withShipment(String orderNumber, Shipment shipment);
    PurchaseOrder withDelivery(String orderNumber, Delivery delivery);
}
