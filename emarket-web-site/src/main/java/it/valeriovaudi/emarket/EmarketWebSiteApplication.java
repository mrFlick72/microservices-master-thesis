package it.valeriovaudi.emarket;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class EmarketWebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmarketWebSiteApplication.class, args);
	}

}

@RestController
class AccountServiceProxy {

	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
	@GetMapping("/api/account-service/account")
	public ResponseEntity accountProxy() throws MalformedURLException {
		URI uri = URI.create("http://api-gateway-server/api/v1/account-service/account");
		RequestEntity<Void> build = RequestEntity.get(uri).build();
		ResponseEntity<String> exchange = oAuth2RestTemplate.exchange(build, String.class);
		return ResponseEntity.ok(exchange.getBody());
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
	@GetMapping("/api/product-catalog-service/price-list")
	public ResponseEntity productProxy() throws MalformedURLException {
		URI uri = URI.create("http://api-gateway-server/api/v1/product-catalog-service/price-list");
		RequestEntity<Void> build = RequestEntity.get(uri).build();
		ResponseEntity<String> exchange = oAuth2RestTemplate.exchange(build, String.class);
		return ResponseEntity.ok(exchange.getBody());
	}

}