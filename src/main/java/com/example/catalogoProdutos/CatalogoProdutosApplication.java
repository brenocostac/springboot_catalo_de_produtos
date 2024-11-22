package com.example.catalogoProdutos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CatalogoProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoProdutosApplication.class, args);
	}

}
