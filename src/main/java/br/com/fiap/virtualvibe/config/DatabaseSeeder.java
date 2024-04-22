package br.com.fiap.virtualvibe.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.virtualvibe.model.Playstation;
import br.com.fiap.virtualvibe.repository.PlaystationRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
 
   @Autowired
   PlaystationRepository playstationRepository;

    @Override
    public void run(String... args) throws Exception {
        playstationRepository.saveAll(
            List.of(
                Playstation.builder().id(1L).titulo(null).preco(null)
            )
        );
    }
    
}
