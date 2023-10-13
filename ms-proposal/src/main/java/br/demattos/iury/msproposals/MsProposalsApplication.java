package br.demattos.iury.msproposals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsProposalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProposalsApplication.class, args);
	}

}
