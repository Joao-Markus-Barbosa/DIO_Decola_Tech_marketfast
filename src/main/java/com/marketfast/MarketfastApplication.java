package com.marketfast;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url="/", description = "Default Server URL")})
@EntityScan(basePackages = {
		"com.marketfast.domain.cliente.model",
		"com.marketfast.domain.pedido.model",
		"com.marketfast.domain.produto.model"
})
@EnableJpaRepositories(basePackages = {
		"com.marketfast.domain.cliente.repository",
		"com.marketfast.domain.pedido.repository",
		"com.marketfast.domain.produto.repository"
})
public class MarketfastApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketfastApplication.class, args);
	}
}
