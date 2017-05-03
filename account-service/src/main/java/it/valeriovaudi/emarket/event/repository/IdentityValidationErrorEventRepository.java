package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.IdentityValidationErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


/**
 * Created by mrflick72 on 03/05/17.
 */

public interface IdentityValidationErrorEventRepository extends CassandraRepository<IdentityValidationErrorEvent> {
}
