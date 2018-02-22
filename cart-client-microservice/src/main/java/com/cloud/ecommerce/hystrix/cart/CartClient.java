package com.cloud.ecommerce.hystrix.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by SMalik on 02-19-2018.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class CartClient {

	public static void main(String[] args) {
        SpringApplication.run(CartClient.class, args);
    }
}
