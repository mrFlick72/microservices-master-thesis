package it.valeriovaudi.emarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

/**
 * Created by mrflick72 on 10/05/17.
 */

@EnableConfigurationProperties(CassandraProperties.class)
@Configuration
class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Autowired
    private CassandraProperties cassandraProperties;

    @Override
    public String[] getEntityBasePackages() {
        return new String[] { "it.valeriovaudi.emarket.event"};
    }

    @Override
    protected String getKeyspaceName() {
        return cassandraProperties.getKeyspaceName();
    }

}
