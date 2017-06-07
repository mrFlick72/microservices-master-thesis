package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.PurchaseOrderErrorEvent;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderErrorEventRepository extends MongoRepository<PurchaseOrderErrorEvent, String> {
}
