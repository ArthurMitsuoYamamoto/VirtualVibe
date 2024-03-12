package br.com.fiap.virtualvibe.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.virtualvibe.record.NintendoRecord;


@RestController
@RequestMapping("nintendo-games")
public class NintendoController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     List<NintendoRecord> repository = new ArrayList<>();

     @GetMapping  
     public List<NintendoRecord> index(){
        return repository;
    }

    @PostMapping
    public ResponseEntity<NintendoRecord> create(@RequestBody NintendoRecord gameNintendo){ //binding
        log.info("Cadastrando game {}", gameNintendo);
        repository.add(gameNintendo);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameNintendo);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<NintendoRecord> show(@PathVariable Long id){
        log.info("buscando game com id {}", id);
        
        for(NintendoRecord gameNintendo: repository){
            if (gameNintendo.id().equals(id))
                return ResponseEntity.status(HttpStatus.OK).body(gameNintendo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<NintendoRecord> update(@PathVariable Long id, @RequestBody NintendoRecord updatedGame) {
        for (int i = 0; i <= repository.size(); i++) {
            if (repository.get(i).id().equals(id)) {
                repository.set(i, updatedGame);
                return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

         for(NintendoRecord game : repository){
            if(game.id().equals(id)){
                repository.remove(game);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}