package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by mrflick72 on 30/05/17.
 */
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {

    Stream<PurchaseOrder> findByUserName(String userName);
    CompletableFuture<PurchaseOrder> findByUserNameAndOrderNumber(String userName, String orderNumber);
}
