package it.valeriovaudi.emarket.endpoint.restfull;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.hateoas.GoodsInPurchaseOrderHateoasFactory;
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
public class GoodsInPurchaseOrderRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    private GoodsInPurchaseOrderHateoasFactory goodsInPriceListHateoasFactory;

    @GetMapping("/{orderNumber}/goods")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getGoodsDataInPuchaseOrder(@PathVariable String orderNumber){
        PurchaseOrder purchaseOrder =
                purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber);
        return ResponseEntity.ok(goodsInPriceListHateoasFactory.toResources(orderNumber, purchaseOrder.getGoodsList()));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{orderNumber}/goods/{goods}/price-list/{priceList}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity saveGoodsDataInPuchaseOrder(@PathVariable String orderNumber, @PathVariable String priceList,
                                                      @PathVariable String goods, @RequestBody Integer quantity){
        purchaseOrderService.saveGoodsInPurchaseOrder(orderNumber, priceList, goods, quantity);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{orderNumber}/goods/{goods}/price-list/{priceList}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity removeGoodsDataInPuchaseOrder(@PathVariable String orderNumber, @PathVariable String priceList,
                                                        @PathVariable String goods){
        purchaseOrderService.removeGoodsInPurchaseOrder(orderNumber, priceList,goods);
        return ResponseEntity.noContent().build();
    }

}
