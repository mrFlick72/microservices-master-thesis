package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.PurchaseOrderErrorEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderErrorEventRepository extends MongoRepository<PurchaseOrderErrorEvent, String> {
}
