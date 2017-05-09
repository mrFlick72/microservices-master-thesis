package it.valeriovaudi.emarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


//@EnableOAuth2Sso
@EnableZuulProxy
@SpringBootApplication
public class RouterGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouterGatewayApplication.class, args);
	}
}

/*
@Configuration
class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
}

@Configuration
class Sec extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login","/account*/
/**").permitAll()
				.anyRequest().authenticated()
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
}
*/
