package com.indra.hexagonalswebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@SpringBootApplication
public class HexagonalSwebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonalSwebfluxApplication.class, args);
	}

}
