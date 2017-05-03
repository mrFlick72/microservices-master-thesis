package it.valeriovaudi.emarket;

import com.datastax.driver.core.Cluster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
//@EnableLoadTimeWeaving
//@EnableSpringConfigured
@EnableCassandraRepositories
@EnableConfigurationProperties(CassandraProperties.class)
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	public SimpleUserTypeResolver simpleUserTypeResolver(Cluster cluster, CassandraProperties cassandraProperties){
		return new SimpleUserTypeResolver(cluster,cassandraProperties.getKeyspaceName());
	}
}
