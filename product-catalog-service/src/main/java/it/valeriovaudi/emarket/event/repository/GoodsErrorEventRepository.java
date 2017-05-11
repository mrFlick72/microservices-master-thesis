package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.GoodsErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by vvaudi on 10/05/17.
 */

public interface GoodsErrorEventRepository extends CassandraRepository<GoodsErrorEvent> {
}
