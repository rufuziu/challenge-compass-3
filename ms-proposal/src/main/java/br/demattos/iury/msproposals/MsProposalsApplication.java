package br.demattos.iury.msproposals;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableRabbit
public class MsProposalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProposalsApplication.class, args);
	}

}
