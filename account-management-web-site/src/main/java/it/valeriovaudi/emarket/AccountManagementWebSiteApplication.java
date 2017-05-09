package it.valeriovaudi.emarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableEurekaClient
@SpringBootApplication
public class AccountManagementWebSiteApplication {

	public static final String LOG_IN = "http://localhost:5050/v1/account-service/login";


	public static void main(String[] args) {
		SpringApplication.run(AccountManagementWebSiteApplication.class, args);
	}
}

@Configuration
class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
	}
}

/*
@Configuration
@EnableOAuth2Sso
//@EnableOAuth2Client
class SocialApplication extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("*/
/**")
				.authorizeRequests()
				.antMatchers(LOG_IN, "/webjars*/
/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin().loginPage(LOG_IN);
	}

}

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception{
		resources.
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable()
				.authorizeRequests().mvcMatchers(LOG_IN).permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage(LOG_IN).failureForwardUrl(LOG_IN);
	}

}*/
