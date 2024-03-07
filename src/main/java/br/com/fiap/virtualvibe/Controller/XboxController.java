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

import br.com.fiap.virtualvibe.record.Xbox;


@RestController
@RequestMapping("xbox-games")
public class XboxController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     List<Xbox> repository = new ArrayList<>();

     @GetMapping  
     public List<Xbox> index(){
        return repository;
    }

    @PostMapping
    public ResponseEntity<Xbox> create(@RequestBody Xbox gameXbox){ //binding
        log.info("Cadastrando game {}", gameXbox);
        repository.add(gameXbox);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameXbox);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Xbox> show(@PathVariable Long id){
        log.info("buscando game com id {}", id);
        
        for(Xbox gameXbox: repository){
            if (gameXbox.id().equals(id))
                return ResponseEntity.status(HttpStatus.OK).body(gameXbox);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Xbox> update(@PathVariable Long id, @RequestBody Xbox updatedGame) {
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

         for(Xbox gameXbox : repository){
            if(gameXbox.id().equals(id)){
                repository.remove(gameXbox);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}