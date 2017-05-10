package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.BaseErrorEvent;
import it.valeriovaudi.emarket.event.model.GoodsErrorEvent;
import it.valeriovaudi.emarket.event.model.GoodsEventTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by vvaudi on 10/05/17.
 */

public interface GoodsErrorEventRepository extends CassandraRepository<GoodsErrorEvent> {
}
