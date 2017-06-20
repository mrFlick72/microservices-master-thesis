package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.PurchaseOrderHateoasFactory;
import it.valeriovaudi.emarket.model.PurchaseOrderStatusEnum;
import it.valeriovaudi.emarket.security.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Security;

/**
 * Created by mrflick72 on 30/05/17.
 */


@Data
@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrderList(){
        return ResponseEntity.ok(purchaseOrderHateoasFactory.toResources(purchaseOrderService.findPurchaseOrderList()));
    }

    @GetMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrder(@PathVariable String orderNumber){
        return ResponseEntity.ok(purchaseOrderHateoasFactory.toResource(purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber)));
    }

    @PostMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createPuchaseOrder(@PathVariable String orderNumber){
        return ResponseEntity.ok(purchaseOrderService.findPurchaseOrderList(orderNumber));
    }

    @PatchMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity pathcPuchaseOrder(@PathVariable String orderNumber, @RequestBody PurchaseOrderStatusEnum purchaseOrderStatusEnum){
        purchaseOrderService.changeStatus(orderNumber, purchaseOrderStatusEnum);
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
