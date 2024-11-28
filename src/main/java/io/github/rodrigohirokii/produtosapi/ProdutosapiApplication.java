package io.github.rodrigohirokii.produtosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ProdutosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutosapiApplication.class, args);
	}

}
