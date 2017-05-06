package it.valeriovaudi.emarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class RouterGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouterGatewayApplication.class, args);
	}
}
