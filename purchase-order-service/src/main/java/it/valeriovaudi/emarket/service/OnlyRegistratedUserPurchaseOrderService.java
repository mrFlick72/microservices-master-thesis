package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.*;
import it.valeriovaudi.emarket.repository.PurchaseOrderRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@Slf4j
@Service
public class OnlyRegistratedUserPurchaseOrderService implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder createPurchaseOrder() {
        String userName = getPrincipalUserName();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(UUID.randomUUID().toString());
        purchaseOrder.setOrderDate(new Date());
        purchaseOrder.setUserName(userName);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(String orderNumber) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        if(one != null){
            purchaseOrderRepository.delete(one);
        }
    }

    @Override
    public PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        return purchaseOrderRepository.save(Optional.ofNullable(one)
                .map(purchaseOrder -> {
                    purchaseOrder.setPurchaseOrderStatusEnum(purchaseOrderStatusEnum);
                    return purchaseOrder;
                }).orElseThrow(RuntimeException::new));
    }

    @Override
    public PurchaseOrder withCustomer(String orderNumber, String userName, Customer customer) {
        Optional.ofNullable(customer).ifPresent(customerAux -> {
            throw new UnsupportedOperationException();
        });
        return null;
    }

    @Override
    public PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact) {
        Optional.ofNullable(customerContact).ifPresent(customerAux -> {
            throw new UnsupportedOperationException();
        });
        return null;
    }

    @Override
    public PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact) {
        if (Optional.ofNullable(customer).isPresent() || Optional.ofNullable(customerContact).isPresent()){
            throw new UnsupportedOperationException();
        }

        // find the purchase order

        // find the customer and customerContact data

        // apply anticorruptation

        // apply modification

        // save on mongo
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
        // find the purchase order
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(orderNumber);

        // apply modification
        purchaseOrder.setShipment(shipment);

        // save on mongo
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder withDelivery(String orderNumber, Delivery delivery) {
        // find the purchase order
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(orderNumber);

        // apply modification
        purchaseOrder.setDelivery(delivery);

        // save on mongo
        return purchaseOrderRepository.save(purchaseOrder);
    }


    private String getPrincipalUserName(){
        String userNAme = "";
        try{
            userNAme = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Throwable t){
            // ignore it
            log.info("session without an authenticated user");
        }
        return userNAme;
    }
}
