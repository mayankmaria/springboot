package com.mayank.microservices.apigateway.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> RouterFunction = 
				p -> p.path("/get")
					  .filters(f -> f.addRequestHeader("MyHeader", "MyUri")
							  		 .addRequestParameter("MyParam", "MyUriParam"))
					  .uri("http://httpbin.org:80");
		return builder.routes()
					  .route(RouterFunction)
					  .route(p -> p.path("/currency-exchange/**")
							       .uri("lb://currency-exchange"))
					  .route(p -> p.path("/currency-conversion/**")
						       .uri("lb://currency-conversion"))
					  .route(p -> p.path("/currency-conversion-feign/**")
						       .uri("lb://currency-conversion"))
					  .route(p -> p.path("/currency-conversion-new/**")
							   .filters(f -> 
							   f.rewritePath("/currency-conversion-new/(?<segment>.*)", 
									   "/currency-conversion-feign/${segment}"))
						       .uri("lb://currency-conversion"))
					  .build();
	}
}
