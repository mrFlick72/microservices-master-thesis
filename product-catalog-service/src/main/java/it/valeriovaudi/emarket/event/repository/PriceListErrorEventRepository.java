package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.BaseErrorEvent;
import it.valeriovaudi.emarket.event.model.PriceListErrorEvent;
import it.valeriovaudi.emarket.event.model.PriceListEventTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by vvaudi on 10/05/17.
 */

public interface PriceListErrorEventRepository extends CassandraRepository<PriceListErrorEvent> {
}
