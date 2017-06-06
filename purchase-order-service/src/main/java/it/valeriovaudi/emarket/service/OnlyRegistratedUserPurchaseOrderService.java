package it.valeriovaudi.emarket.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
    @HystrixCommand
    public PurchaseOrder findPurchaseOrder(String userName, String orderNumber) {
        try {
            return purchaseOrderRepository.findByUserNameAndOrderNumber(userName, orderNumber).get(2*60, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error(String.format("%s: %s", "error cause message", e.getCause().getMessage()));
            log.error(String.format("%s: %s", "error message", e.getMessage()));
        }
        return null;
    }

    @Override
    @HystrixCommand
    public List<PurchaseOrder> findPurchaseOrderList() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    @HystrixCommand
    public List<PurchaseOrder> findPurchaseOrderList(String userName) {
        return purchaseOrderRepository.findByUserName(userName).collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder createPurchaseOrder() {
        String userName = getPrincipalUserName();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(UUID.randomUUID().toString());
        purchaseOrder.setOrderDate(new Date());
        purchaseOrder.setUserName(userName);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    @HystrixCommand
    public void deletePurchaseOrder(String orderNumber) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        if(one != null){
            purchaseOrderRepository.delete(one);
        }
    }

    @Override
    @HystrixCommand
    public PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum) {
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        return purchaseOrderRepository.save(Optional.ofNullable(one)
                .map(purchaseOrder -> {
                    purchaseOrder.setPurchaseOrderStatusEnum(purchaseOrderStatusEnum);
                    return purchaseOrder;
                }).orElseThrow(RuntimeException::new));
    }

    @Override
    @HystrixCommand
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
    @HystrixCommand
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
    @HystrixCommand
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
    @HystrixCommand
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
    @HystrixCommand
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
    @HystrixCommand
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
