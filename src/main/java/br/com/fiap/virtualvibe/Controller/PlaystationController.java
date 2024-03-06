package br.com.fiap.virtualvibe.Controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import br.com.fiap.virtualvibe.model.Playstation;


@RestController
@RequestMapping("ps-games")
public class PlaystationController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     List<Playstation> repository = new ArrayList<>();

     @GetMapping  
     public List<Playstation> index(){
        return repository;
    }

    @PostMapping
    public ResponseEntity<Playstation> create(@RequestBody Playstation gamePlaystation){ //binding
        log.info("Cadastrando game {}", gamePlaystation);
        repository.add(gamePlaystation);
        return ResponseEntity.status(HttpStatus.CREATED).body(gamePlaystation);
    }
    


     @GetMapping("{id}")
    public ResponseEntity<Playstation> show(@PathVariable Long id){
        log.info("buscando game com id {}", id);
        
        for(Playstation gamePlaystation: repository){
            if (gamePlaystation.id().equals(id))
            return ResponseEntity.status(HttpStatus.OK).body(gamePlaystation);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }    



   @PutMapping("{id}")
    public ResponseEntity<Playstation> update(@PathVariable Long id, @RequestBody Playstation updatedGame) {
        for (int i = 0; i < repository.size(); i++) {
            Playstation gamePlaystation = repository.get(i);
            if (gamePlaystation.id().equals(id)) {
                updatedGame = new Playstation(id, updatedGame.titulo(), updatedGame.preco(), updatedGame.descricao());
                repository.set(i, updatedGame);
                return ResponseEntity.ok(updatedGame);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Iterator<Playstation> iterator = repository.iterator();
        while (iterator.hasNext()) {
            Playstation gamePlaystation = iterator.next();
            if (gamePlaystation.id().equals(id)) {
                iterator.remove();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
            }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}

