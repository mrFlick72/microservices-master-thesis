package it.valeriovaudi.emarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@EnableEurekaClient
@SpringBootApplication
public class GuestWebSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestWebSiteApplication.class, args);
	}
}

@Controller
class IndexController {

	@GetMapping
	public String index(){
		return "index";
	}
}
