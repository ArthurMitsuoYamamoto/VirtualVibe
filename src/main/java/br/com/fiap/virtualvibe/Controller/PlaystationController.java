package br.com.fiap.virtualvibe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.virtualvibe.model.Playstation;

import br.com.fiap.virtualvibe.record.PlaystationRecord;
import br.com.fiap.virtualvibe.repository.PlaystationRepository;


@RestController
@RequestMapping("ps-games")
public class PlaystationController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     List<PlaystationRecord> repository = new ArrayList<>();


      @Autowired // Injeção de Dependência - Inversão de Controle
     PlaystationRepository playstationRepository;

     @GetMapping  
     public List<Playstation> index(){
        return playstationRepository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Playstation create(@RequestBody Playstation gamePlaystation){ //binding
        log.info("Cadastrando game {}", gamePlaystation);
        return playstationRepository.save(gamePlaystation);
      
    }
    
    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Playstation> show(@PathVariable Long id){
        log.info("buscando game com id {}", id);
        return playstationRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable long id){
        log.info("apagando playstation game", id);

        var gameEncontrado = getGameById(id);
        if(gameEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
    
            repository.remove(gameEncontrado.get());
           
            return ResponseEntity.noContent().build();

        
    }

    @PutMapping("{id}")
    public ResponseEntity<PlaystationRecord> update(@PathVariable long id, @RequestBody PlaystationRecord gamePlaystation){
        log.info("atualizando game {} para {}", id, gamePlaystation);
        var gameEncontrado = getGameById(id);

        if (gameEncontrado.isEmpty())
            return ResponseEntity.notFound().build();

        var gameAntigo = gameEncontrado.get();

        var gameNovo = new PlaystationRecord(id, gamePlaystation.titulo(), gamePlaystation.preco(), gamePlaystation.descricao());
        
        repository.remove(gameAntigo);

        repository.add(gameNovo);
        
        return ResponseEntity.ok(gameNovo);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Optional<PlaystationRecord> getGameById(long id) {
        var gameEncontrado = repository
        .stream()
        .filter(g->!g.id().equals(id))
        .findFirst();
        return gameEncontrado;
    }
    


    
    
    
    
    
    
    
    
    
    //update old version
    // @PutMapping("{id}")
    // public ResponseEntity<Playstation> update(@PathVariable Long id, @RequestBody Playstation updatedGame) {
    //     for (int i = 0; i <= repository.size(); i++) {
    //         if (repository.get(i).id().equals(id)) {
    //             repository.set(i, updatedGame);
    //             return ResponseEntity.status(HttpStatus.OK).body(updatedGame);
    //         }
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }
    
    
   
   
    // @DeleteMapping("{id}")
    // public ResponseEntity<Void> delete(@PathVariable Long id) {

    //      for(Playstation gamePlaystation : repository){
    //         if(gamePlaystation.id().equals(id)){
    //             repository.remove(gamePlaystation);
    //             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    //         }
    //     }
        
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }
}



    //procurar id especifico -- old version
    // for(Playstation gamePlaystation: repository){
        //     if (gamePlaystation.id().equals(id))
        //         return ResponseEntity.status(HttpStatus.OK).body(gamePlaystation);
        // }
        // todo refatorar com stream




        // @DeleteMapping("{id}")
    // public ResponseEntity<Void> delete(@PathVariable Long id) {

    //      for(Playstation gamePlaystation : repository){
    //         if(gamePlaystation.id().equals(id)){
    //             repository.remove(gamePlaystation);
    //             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    //         }
    //     }
        
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }