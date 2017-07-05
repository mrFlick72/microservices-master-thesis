package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.DeliveryHateoasFactory;
import it.valeriovaudi.emarket.model.Delivery;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mrflick72 on 13/06/17.
 */

@Data
@RestController
@RequestMapping("/purchase-order")
public class DeliveryRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    private DeliveryHateoasFactory deliveryHateoasFactory;

    @GetMapping("/{orderNumber}/delivery")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getDeliveryDataPuchaseOrder(@PathVariable String orderNumber){
        PurchaseOrder purchaseOrder =
                purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber);
        return ResponseEntity.ok(deliveryHateoasFactory.toResource(orderNumber, purchaseOrder.getDelivery()));
    }

    @PutMapping("/{orderNumber}/delivery")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deliveryDataPuchaseOrder(@PathVariable String orderNumber, @RequestBody Delivery delivery){
        purchaseOrderService.withDelivery(orderNumber, delivery);
        return ResponseEntity.noContent().build();
    }

}
