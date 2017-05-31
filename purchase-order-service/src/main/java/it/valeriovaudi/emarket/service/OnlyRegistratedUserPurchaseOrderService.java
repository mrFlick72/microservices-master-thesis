package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.*;
import it.valeriovaudi.emarket.repository.PurchaseOrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@Service
public class OnlyRegistratedUserPurchaseOrderService implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    @Override
    public PurchaseOrder createPurchaseOrder() {
        return null;
    }

    @Override
    public PurchaseOrder deletePurchaseOrder(String orderNumber) {
        return null;
    }

    @Override
    public PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum) {
        return null;
    }

    @Override
    public PurchaseOrder withCustomer(String orderNumber, String userName, Customer customer) {
        return null;
    }

    @Override
    public PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact) {
        return null;
    }

    @Override
    public PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact) {
        return null;
    }

    @Override
    public PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber, String goodsId) {
        return null;
    }

    @Override
    public PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber, String goodsId) {
        return null;
    }

    @Override
    public PurchaseOrder withShipment(String orderNumber, Shipment shipment) {
        return null;
    }

    @Override
    public PurchaseOrder withDelivery(String orderNumber, Delivery delivery) {
        return null;
    }
}
