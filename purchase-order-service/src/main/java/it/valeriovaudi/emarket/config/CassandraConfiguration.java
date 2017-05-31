package it.valeriovaudi.emarket.config;

import com.datastax.driver.core.Cluster;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;

/**
 * Created by mrflick72 on 10/05/17.
 */

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
class CassandraConfiguration extends CassandraDataAutoConfiguration {

    public CassandraConfiguration(BeanFactory beanFactory,
                                  CassandraProperties properties,
                                  Cluster cluster, Environment environment) {
        super(beanFactory, properties, cluster, environment);
    }

    @Bean
    public CassandraMappingContext cassandraMapping(BeanFactory beanFactory, Cluster cluster, CassandraProperties properties) throws ClassNotFoundException {
        BasicCassandraMappingContext context = new BasicCassandraMappingContext();
        context.setInitialEntitySet(CassandraEntityClassScanner.scan("it.valeriovaudi.emarket.event"));
        context.setUserTypeResolver(new SimpleUserTypeResolver(cluster,
                properties.getKeyspaceName()));
        return context;
    }
}
