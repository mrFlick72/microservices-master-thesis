package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.service.PurchaseOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mrflick72 on 30/05/17.
 */


@Data
@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderRestFullEndPoint {


    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/user-name/{userName}/order-number/{orderNumber}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getPuchaseOrderList(@PathVariable String userName, @PathVariable String orderNumber){
        return null;
    }
}
