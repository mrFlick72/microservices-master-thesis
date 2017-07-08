package it.valeriovaudi.emarket;

import it.valeriovaudi.emarket.event.config.EventMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
@EnableBinding(EventMessageChannels.class)
public class PurchaseOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseOrderServiceApplication.class, args);
	}
}
