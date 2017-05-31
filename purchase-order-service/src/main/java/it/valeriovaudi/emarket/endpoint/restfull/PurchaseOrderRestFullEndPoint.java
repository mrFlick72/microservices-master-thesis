package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.service.PurchaseOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
}
