package br.com.fiap.virtualvibe.controller;

import java.util.List;
import java.util.Optional;

import br.com.fiap.virtualvibe.model.Xbox;
import br.com.fiap.virtualvibe.repository.XboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.virtualvibe.record.XboxRecord;


@RestController
@RequestMapping("xbox-games")
public class XboxController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     @Autowired
     XboxRepository xboxRepository;

     @GetMapping  
     public List<Xbox> index(){
         log.info("[XBOX] Buscando todos os jogos");

        return xboxRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Xbox> create(@RequestBody XboxRecord gameXboxRecord){ //binding
        log.info("[XBOX] Cadastrando jogo {}", gameXboxRecord);

        Xbox game = Xbox.builder()
                .titulo(gameXboxRecord.titulo())
                .preco(gameXboxRecord.preco())
                .descricao(gameXboxRecord.descricao())
                .build();

        Xbox savedGame = xboxRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Xbox> show(@PathVariable Long id){
        log.info("[XBOX] Buscando game com id {}", id);

        Optional<Xbox> gameOpt = xboxRepository.findById(id);

        if(gameOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(gameOpt.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Xbox> update(@PathVariable Long id, @RequestBody XboxRecord updatedGame) {
        log.info("[XBOX] Atualizando game com id {}", id);

        Optional<Xbox> gameOpt = xboxRepository.findById(id);

        if(gameOpt.isPresent()){

            Xbox xbox = gameOpt.get();
            xbox.setTitulo(updatedGame.titulo());
            xbox.setPreco(updatedGame.preco());
            xbox.setDescricao(updatedGame.descricao());

            return ResponseEntity.status(HttpStatus.OK).body(xboxRepository.save(xbox));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("[XBOX] Deletando game com id {}", id);

        Optional<Xbox> xboxGame = xboxRepository.findById(id);

        if(xboxGame.isPresent()){
            xboxRepository.delete(xboxGame.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}