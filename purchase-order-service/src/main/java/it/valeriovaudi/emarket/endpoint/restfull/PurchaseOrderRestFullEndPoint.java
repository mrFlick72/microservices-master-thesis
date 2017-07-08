package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.PurchaseOrderHateoasFactory;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import it.valeriovaudi.emarket.model.PurchaseOrderStatusEnum;
import it.valeriovaudi.emarket.security.SecurityUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;
import java.util.List;

/**
 * Created by mrflick72 on 30/05/17.
 */


@Data
@Slf4j
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
    public ResponseEntity getPuchaseOrderList(@RequestParam(value = "withOnlyOrderId", defaultValue = "false") boolean withOnlyOrderId){

        Boolean role_user = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));

        List<PurchaseOrder> purchaseOrders = role_user ?
                purchaseOrderService.findPurchaseOrderList(securityUtils.getPrincipalUserName(), withOnlyOrderId) :
                purchaseOrderService.findPurchaseOrderList(withOnlyOrderId);

        return ResponseEntity.ok(purchaseOrderHateoasFactory.toResources(purchaseOrders));
    }

    @GetMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrder(@PathVariable String orderNumber){
        return ResponseEntity.ok(purchaseOrderHateoasFactory.toResource(purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createPuchaseOrder(Principal principal){
        PurchaseOrder purchaseOrder = purchaseOrderService.createPurchaseOrder();
        purchaseOrderService.withCustomerAndCustomerContact(purchaseOrder.getOrderNumber(), principal.getName(), null, null);

        return ResponseEntity.created(MvcUriComponentsBuilder.fromMethodName(PurchaseOrderRestFullEndPoint.class,
                "getPuchaseOrder",purchaseOrder.getOrderNumber()).build().toUri()).build();
    }

    @PatchMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity pathcPuchaseOrder(@PathVariable String orderNumber, @RequestBody String purchaseOrderStatusEnum){
        purchaseOrderService.changeStatus(orderNumber, PurchaseOrderStatusEnum.valueOf(purchaseOrderStatusEnum.toUpperCase()));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deletePuchaseOrder(@PathVariable String orderNumber){
        purchaseOrderService.deletePurchaseOrder(orderNumber);
        return ResponseEntity.noContent().build();
    }
}
