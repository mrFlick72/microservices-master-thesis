package it.valeriovaudi.emarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vvaudi on 27/05/17.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/public/index").setViewName("public/index");
        registry.addViewController("/private/index").setViewName("private/index");
    }
}
