package it.valeriovaudi.emarket.endpoint.restfull;

import it.valeriovaudi.emarket.security.SecurityUtils;
import it.valeriovaudi.emarket.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mrflick72 on 13/06/17.
 */
public abstract class AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    protected PurchaseOrderService purchaseOrderService;

    @Autowired
    protected SecurityUtils securityUtils;
}
