package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.model.Delivery;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import it.valeriovaudi.emarket.model.PurchaseOrderStatusEnum;
import it.valeriovaudi.emarket.model.Shipment;
import it.valeriovaudi.emarket.service.PurchaseOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by mrflick72 on 30/05/17.
 */


@Data
@RestController
@RequestMapping("/purchase-order/user-name/{userName}")
public class PurchaseOrderRestFullEndPoint {


    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrderList(){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrderList());
    }

    @GetMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER') and #contact.name == authentication.name")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrder(@PathVariable String orderNumber, Principal principal){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrder(principal.getName(), orderNumber));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createPuchaseOrder(@PathVariable String orderNumber){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrderList(orderNumber));
    }

    @PatchMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity pathcPuchaseOrder(@PathVariable String orderNumber, @RequestBody PurchaseOrderStatusEnum purchaseOrderStatusEnum){
        purchaseOrderService.changeStatus(orderNumber, purchaseOrderStatusEnum);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{orderNumber}/customer/{userName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity customerDataPuchaseOrder(@PathVariable String orderNumber, @PathVariable String userName){
        purchaseOrderService.withCustomerAndCustomerContact(orderNumber, userName, null, null);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{orderNumber}/delivery")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deliveryDataPuchaseOrder(@PathVariable String orderNumber, @RequestBody Delivery delivery){
        purchaseOrderService.withDelivery(orderNumber, delivery);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{orderNumber}/shipment")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity shipmentDataPuchaseOrder(@PathVariable String orderNumber, @RequestBody Shipment shipment){
        purchaseOrderService.withShipment(orderNumber, shipment);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{orderNumber}/price-list/{priceList}/goods/{goods}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity saveGoodsDataInPuchaseOrder(@PathVariable String orderNumber, @PathVariable String priceList,
                                                   @PathVariable String goods, @RequestBody Integer quantity){
        purchaseOrderService.saveGoodsInPurchaseOrder(orderNumber, priceList, goods, quantity);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderNumber}/goods-barcode/{goodsBarCode}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity removeGoodsDataInPuchaseOrder(@PathVariable String orderNumber, @PathVariable String goodsBarCode){
        purchaseOrderService.removeGoodsInPurchaseOrder(orderNumber, goodsBarCode);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderNumber")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deletePuchaseOrder(@PathVariable String orderNumber){
        purchaseOrderService.deletePurchaseOrder(orderNumber);
        return ResponseEntity.notFound().build();
    }
}
