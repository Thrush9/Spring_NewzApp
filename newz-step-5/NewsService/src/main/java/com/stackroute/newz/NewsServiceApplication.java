package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.newz.jwtfilter.JwtFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */

@SpringBootApplication
public class NewsServiceApplication {

	/*
	 * Define the bean for Filter registration. Create a new FilterRegistrationBean
	 * object and use setFilter() method to set new instance of JwtFilter object.
	 * Also specifies the Url patterns for registration bean.
	 */


    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
    	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
 		CorsConfiguration config=new CorsConfiguration();
 		config.setAllowCredentials(true);
 		config.addAllowedOrigin("*");
 		config.addAllowedMethod("*");
 		config.addAllowedHeader("*");
 		urlconfig.registerCorsConfiguration("/**", config);
 		FilterRegistrationBean filterbean = new FilterRegistrationBean(new CorsFilter(urlconfig));
 		filterbean.setFilter(new JwtFilter());
 		filterbean.addUrlPatterns("/api/*");
 		return filterbean;
    }
    
	/*
	 * You need to run SpringApplication.run, because this method start whole spring
	 * framework. Code below integrates your main() with SpringBoot
	 */

	public static void main(String[] args) {
		SpringApplication.run(NewsServiceApplication.class, args);
	}

}
