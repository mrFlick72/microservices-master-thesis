package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {
}
