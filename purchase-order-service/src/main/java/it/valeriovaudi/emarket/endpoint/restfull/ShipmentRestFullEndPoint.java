package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.ShipmentHateoasFactory;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import it.valeriovaudi.emarket.model.Shipment;
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
public class ShipmentRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    private ShipmentHateoasFactory shipmentHateoasFactory;

    @GetMapping("/{orderNumber}/shipment")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getShipmentDataPuchaseOrder(@PathVariable String orderNumber){
        PurchaseOrder purchaseOrder =
                purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber);
        return ResponseEntity.ok(shipmentHateoasFactory.toResource(orderNumber, purchaseOrder.getShipment()));
    }
    @PutMapping("/{orderNumber}/shipment")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity shipmentDataPuchaseOrder(@PathVariable String orderNumber, @RequestBody Shipment shipment){
        purchaseOrderService.withShipment(orderNumber, shipment);
        return ResponseEntity.noContent().build();
    }
}
