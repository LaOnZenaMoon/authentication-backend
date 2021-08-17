package me.lozm;

import me.lozm.global.filters.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableRetry
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class JwtApiApplication {

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("*")
						.allowedMethods("*")
						.allowedOrigins("*");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtApiApplication.class, args);
	}

}
