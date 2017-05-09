package it.valeriovaudi.emarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableEurekaClient
@EnableOAuth2Client
@EnableResourceServer
@SpringBootApplication
public class ProductCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}
}

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
