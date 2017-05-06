package it.valeriovaudi.emarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableHystrix
//@EnableOAuth2Sso
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}

/*
@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void init(WebSecurity web) {
		web.ignoring().antMatchers("/");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().antMatcher("*/
/**").authorizeRequests().anyRequest().authenticated();
	}

}

class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
}
*/
