package br.com.fatecdiadema.projetotoyoyamongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoToyotaMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoToyotaMongodbApplication.class, args);
        System.out.println("Spring Boot iniciado");
    }

}
