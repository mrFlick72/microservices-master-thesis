package it.valeriovaudi.emarket.event.repository;

import it.valeriovaudi.emarket.event.model.ChangeAccountPasswordEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


/**
 * Created by mrflick72 on 03/05/17.
 */


public interface ChangeAccountPasswordEventRepository extends CassandraRepository<ChangeAccountPasswordEvent> {

}
