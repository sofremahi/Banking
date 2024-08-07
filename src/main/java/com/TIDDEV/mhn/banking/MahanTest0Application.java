package com.TIDDEV.mhn.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Flux;
//
//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@EnableJpaRepositories
public class MahanTest0Application {

	public static void main(String[] args) {
 SpringApplication.run(MahanTest0Application.class, args);

	}
//	@Bean
//	public RouterFunction<ServerResponse> routerFunction() {
//		return route(GET("/functional-mono"),
//				request -> ServerResponse.ok().bodyValue("Hello, Mono from Functional Endpoint!"))
//				.andRoute(GET("/functional-flux"),
//						request -> ServerResponse.ok().body(Flux.just("Hello", "from", "Flux"), String.class));
//	}
}
