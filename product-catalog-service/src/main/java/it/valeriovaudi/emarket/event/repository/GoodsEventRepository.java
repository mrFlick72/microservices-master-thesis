package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.GoodsEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by vvaudi on 10/05/17.
 */

public interface GoodsEventRepository extends CassandraRepository<GoodsEvent> {
}
