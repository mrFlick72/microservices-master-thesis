package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.integration.AccountIntegrationService;
import it.valeriovaudi.emarket.integration.ProductCatalogIntegrationService;
import it.valeriovaudi.emarket.model.*;
import it.valeriovaudi.emarket.repository.PurchaseOrderRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Data
@Slf4j
@Service
public class OnlyRegistratedUserPurchaseOrderService implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductCatalogIntegrationService productCatalogIntegrationService;

    @Autowired
    private AccountIntegrationService accountIntegrationService;


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
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        Customer customerFormAccountData = accountIntegrationService.getCustomerFormAccountData(userName);
        one.setCustomer(customerFormAccountData);

        return purchaseOrderRepository.save(one);
    }

    @Override
    public PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact) {
        Optional.ofNullable(customerContact).ifPresent(customerAux -> {
            throw new UnsupportedOperationException();
        });

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        CustomerContact customerContactFormAccountData = accountIntegrationService.getCustomerContactFormAccountData(userName);
        one.setCustomerContact(customerContactFormAccountData);

        return purchaseOrderRepository.save(one);
    }

    @Override
    public PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact) {
        if (Optional.ofNullable(customer).isPresent() || Optional.ofNullable(customerContact).isPresent()){
            throw new UnsupportedOperationException();
        }

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);

        Customer customerFormAccountData = accountIntegrationService.getCustomerFormAccountData(userName);
        one.setCustomer(customerFormAccountData);

        CustomerContact customerContactFormAccountData = accountIntegrationService.getCustomerContactFormAccountData(userName);
        one.setCustomerContact(customerContactFormAccountData);

        return purchaseOrderRepository.save(one);
    }

    @Override
    public PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber, String priceListId, String goodsId, int quantity) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        Goods goodsInPriceListData = productCatalogIntegrationService.getGoodsInPriceListData(priceListId, goodsId);
        List<Goods> goods = Optional.ofNullable(one.getGoodsList()).orElse(new ArrayList<>());

        goodsInPriceListData.setQuantity(quantity);
        goods.add(goodsInPriceListData);

        one.setGoodsList(goods);

        return purchaseOrderRepository.save(one);
    }

    @Override
    public PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber, String barCode) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        List<Goods> goods = Optional.ofNullable(one.getGoodsList()).orElse(new ArrayList<>())
                .stream().filter(goodsAux -> !goodsAux.getBarCode().equals(barCode))
                .collect(Collectors.toList());

        one.setGoodsList(goods);

        return purchaseOrderRepository.save(one);
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
        String userName = "";
        try{
            userName = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Throwable t){
            // ignore it
            log.error("session without an authenticated user");
        }
        return userName;
    }
}
