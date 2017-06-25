package it.valeriovaudi.emarket.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.event.model.PurchaseOrderErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.*;
import it.valeriovaudi.emarket.integration.AccountIntegrationService;
import it.valeriovaudi.emarket.integration.ProductCatalogIntegrationService;
import it.valeriovaudi.emarket.model.*;
import it.valeriovaudi.emarket.repository.PurchaseOrderRepository;
import it.valeriovaudi.emarket.security.SecurityUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
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

    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    private SecurityUtils securityUtils;

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
        String correlationId = UUID.randomUUID().toString();
        String userName = securityUtils.getPrincipalUserName();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(UUID.randomUUID().toString());
        purchaseOrder.setOrderDate(new Date());
        purchaseOrder.setUserName(userName);

        return doSavePurchaseOrderData(correlationId, purchaseOrder, SaveGoodsInPurchaseOrderException.class);
    }

    @Override
    @HystrixCommand
    public void deletePurchaseOrder(String orderNumber) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);
        purchaseOrderRepository.delete(orderNumber);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

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
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        Optional.ofNullable(customer).ifPresent(customerAux -> {
            throw new UnsupportedOperationException();
        });
        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        Customer customerFormAccountData = accountIntegrationService.getCustomerFormAccountData(userName);
        one.setCustomer(customerFormAccountData);

        return  doSavePurchaseOrderData(correlationId, one, SaveCustomerException.class);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        Optional.ofNullable(customerContact).ifPresent(customerAux -> {
            throw new UnsupportedOperationException();
        });

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        CustomerContact customerContactFormAccountData = accountIntegrationService.getCustomerContactFormAccountData(userName);
        one.setCustomerContact(customerContactFormAccountData);

        return  doSavePurchaseOrderData(correlationId, one, SaveCustomerContactException.class);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        if (Optional.ofNullable(customer).isPresent() || Optional.ofNullable(customerContact).isPresent()){
            throw new UnsupportedOperationException();
        }

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);

        Customer customerFormAccountData = accountIntegrationService.getCustomerFormAccountData(userName);
        one.setCustomer(customerFormAccountData);

        CustomerContact customerContactFormAccountData = accountIntegrationService.getCustomerContactFormAccountData(userName);
        one.setCustomerContact(customerContactFormAccountData);

        return doSavePurchaseOrderData(correlationId, one, SaveCustomerAndOrCustomerContactException.class);
    }

    //todo fixme
    @Override
    @HystrixCommand
    public PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber, String priceListId, String goodsId, int quantity) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        Goods goodsInPriceListData = productCatalogIntegrationService.getGoodsInPriceListData(priceListId, goodsId);
        List<Goods> goods = Optional.ofNullable(one.getGoodsList()).orElse(new ArrayList<>());

        goodsInPriceListData.setQuantity(quantity);

        goods.add(goodsInPriceListData);

        one.setGoodsList(goods);

        return doSavePurchaseOrderData(correlationId, one, SaveGoodsInPurchaseOrderException.class);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber,String priceListId, String goodsId) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        PurchaseOrder one = purchaseOrderRepository.findOne(orderNumber);
        List<Goods> goods = Optional.ofNullable(one.getGoodsList()).orElse(new ArrayList<>()).stream()
                .filter(goodsAux -> !checkGoods(priceListId,goodsId,goodsAux))
                .collect(Collectors.toList());

        one.setGoodsList(goods);

        return doSavePurchaseOrderData(correlationId, one, SaveGoodsInPurchaseOrderException.class);
    }

    @Override
    public PurchaseOrder withShipment(String orderNumber, Shipment shipment) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        // find the purchase order
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(orderNumber);

        // apply modification
        purchaseOrder.setShipment(shipment);

        // save on mongo
        return doSavePurchaseOrderData(correlationId, purchaseOrder, SaveShipmentException.class);
    }

    @Override
    @HystrixCommand
    public PurchaseOrder withDelivery(String orderNumber, Delivery delivery) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPurchaseOrderExist(correlationId, orderNumber);

        // find the purchase order
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(orderNumber);

        // apply modification
        purchaseOrder.setDelivery(delivery);

        // save on mongo
        return doSavePurchaseOrderData(correlationId, purchaseOrder, SaveDeliveryException.class);
    }

    private void doCheckPurchaseOrderExist(String correlationId, String purchaseOrderId) {
        PurchaseOrder purchaseOrder;
        Function<String, PurchaseOrderNotFoundException> f = userNameAux -> {
            eventDomainPubblishService.publishPurchaseOrderErrorEvent(correlationId, purchaseOrderId,
                    null, null, null, EventTypeEnum.READ, PurchaseOrderNotFoundException.DEFAULT_MESSAGE, PurchaseOrderNotFoundException.class);
            return new PurchaseOrderNotFoundException(PurchaseOrderNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            purchaseOrder =  purchaseOrderRepository.findOne(purchaseOrderId);
            if(purchaseOrder== null){
                throw f.apply(correlationId);
            }
        } catch (Exception e){
            throw f.apply(correlationId);
        }
    }

    protected PurchaseOrder doSavePurchaseOrderData(String correlationId, PurchaseOrder purchaseOrder, Class<? extends AbstractException> exception) {
        PurchaseOrder purchaseOrderAux;
        AbstractException abstractException = null;
        try{
            purchaseOrderAux = purchaseOrderRepository.save(purchaseOrder);
        } catch (Exception e){
            abstractException = newAbstractException(exception, e);
            eventDomainPubblishService.publishPurchaseOrderErrorEvent(correlationId, purchaseOrder.getOrderNumber(),
                    null,null, purchaseOrder.getUserName(), EventTypeEnum.SAVE, abstractException.getMessage(), exception);
            throw  abstractException;
        }

        return purchaseOrderAux;
    }

    private boolean checkGoods(String priceListId, String goodsId, Goods goods){
        return goods.getId().equals(goodsId) && goods.getPriceListId().equals(priceListId);
    }

    public AbstractException newAbstractException(Class<? extends AbstractException> exception, Exception e){
        try {
            return (AbstractException) exception.getConstructors()[0].newInstance(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}