package me.lozm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class JwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApiApplication.class, args);
	}

}
