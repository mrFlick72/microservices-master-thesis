package it.valeriovaudi.emarket.event.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@ToString(callSuper = true)
public abstract class AbstractDomainEvent implements Serializable {

    @JsonIgnore
    @PrimaryKey
    protected UUID id;

    @CassandraType(type = DataType.Name.UDT, userTypeName = "eventAuditData")
    protected EventAuditData auditData;
}
