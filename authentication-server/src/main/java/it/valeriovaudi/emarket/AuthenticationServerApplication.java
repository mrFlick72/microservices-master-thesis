package it.valeriovaudi.emarket;

import it.valeriovaudi.emarket.integration.AuthServerAccountServiceBridge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@SpringBootApplication
@IntegrationComponentScan
@EnableBinding(AuthServerAccountServiceBridge.class)
public class AuthenticationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
}
