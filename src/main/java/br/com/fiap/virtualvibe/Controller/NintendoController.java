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

import br.com.fiap.virtualvibe.model.Nintendo;


@RestController
@RequestMapping("nintendo-games")
public class NintendoController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     List<Nintendo> repository = new ArrayList<>();

     @GetMapping  
     public List<Nintendo> index(){
        return repository;
    }

    @PostMapping
    public ResponseEntity<Nintendo> create(@RequestBody Nintendo gameNintendo){ //binding
        log.info("Cadastrando game {}", gameNintendo);
        repository.add(gameNintendo);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameNintendo);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Nintendo> show(@PathVariable Long id){
        log.info("buscando game com id {}", id);
        
        for(Nintendo gameNintendo: repository){
            if (gameNintendo.id().equals(id))
                return ResponseEntity.status(HttpStatus.OK).body(gameNintendo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Nintendo> update(@PathVariable Long id, @RequestBody Nintendo updatedGame) {
        for (int i = 0; i < repository.size(); i++) {
            Nintendo gameNintendo = repository.get(i);
            if (gameNintendo.id().equals(id)) {
                updatedGame = new Nintendo(id, updatedGame.titulo(), updatedGame.preco(), updatedGame.descricao());
                repository.set(i, updatedGame);
                return ResponseEntity.ok(updatedGame);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Iterator<Nintendo> iterator = repository.iterator();
        while (iterator.hasNext()) {
            Nintendo gameNintendo = iterator.next();
            if (gameNintendo.id().equals(id)) {
                iterator.remove();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

