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

import br.com.fiap.virtualvibe.model.Xbox;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(gameXbox);}
    

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
        for (int i = 0; i < repository.size(); i++) {
            Xbox gameXbox = repository.get(i);
            if (gameXbox.id().equals(id)) {
                updatedGame = new Xbox(id, updatedGame.titulo(), updatedGame.preco(), updatedGame.descricao());
                repository.set(i, updatedGame);
                return ResponseEntity.ok(updatedGame);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Iterator<Xbox> iterator = repository.iterator();
        while (iterator.hasNext()) {
            Xbox gameXbox = iterator.next();
            if (gameXbox.id().equals(id)) {
                iterator.remove();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
