package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.endpoint.response.CustomerDataResponseDTO;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrflick72 on 13/06/17.
 */

@Data
@RestController
@RequestMapping("/purchase-order")
public class CustomerRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @GetMapping("/{orderNumber}/customer")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getCustomerDataPuchaseOrder(@PathVariable String orderNumber){
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber);

        CustomerDataResponseDTO customerDataResponseDTO =
                CustomerDataResponseDTO.builder()
                        .customer(purchaseOrder.getCustomer())
                .customerContact(purchaseOrder.getCustomerContact()).build();

        return ResponseEntity.ok(customerDataResponseDTO);
    }

    @PatchMapping("/{orderNumber}/customer")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity customerDataPuchaseOrder(@PathVariable String orderNumber, @RequestBody String userName){
        purchaseOrderService.withCustomerAndCustomerContact(orderNumber, userName, null, null);
        return ResponseEntity.notFound().build();
    }

}