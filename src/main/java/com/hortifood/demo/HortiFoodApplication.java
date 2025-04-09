package com.hortifood.demo;

import com.hortifood.demo.entity.Client;
import com.hortifood.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hortifood.demo.repository")
@EntityScan(basePackages = "com.hortifood.demo.entity")
public class HortiFoodApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HortiFoodApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Client cliente = new Client();
		cliente.setNome("Cliente Teste");
		cliente.setTelefone(123123);
		cliente.setEmailCliente("teste@email.com");
		cliente.setSenhaCliente("123456");

		clientRepository.save(cliente);

	}
}
