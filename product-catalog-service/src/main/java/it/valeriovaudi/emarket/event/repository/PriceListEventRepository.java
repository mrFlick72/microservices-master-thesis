package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.AbstractDomainEvent;
import it.valeriovaudi.emarket.event.model.PriceListEvent;
import it.valeriovaudi.emarket.event.model.PriceListEventTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by vvaudi on 10/05/17.
 */

public interface PriceListEventRepository extends CassandraRepository<PriceListEvent> {

}
