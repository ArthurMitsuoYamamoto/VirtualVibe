package br.com.fiap.virtualvibe.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.virtualvibe.record.XboxRecord;


@RestController
@RequestMapping("xbox-games")
public class XboxController {
    
     Logger log = LoggerFactory.getLogger(getClass());

     //List<XboxRecord> repository = new ArrayList<>();

     @Autowired // Injeção de Dependência - Inversão de Controle
     XboxRepository xboxRepository;

     @GetMapping  
     public List<Xbox> index(){
        return xboxRepository.findAll();
    }

    
    @SuppressWarnings("null")
    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
     public Xbox create(@RequestBody Xbox gameXbox) {
        log.info("Cadastrando gameXbox {}", gameXbox);
       return xboxRepository.save(gameXbox);
     }

   // @PostMapping
    // public ResponseEntity<Xbox> create(@RequestBody XboxRecord gameXboxRecord){ //binding
     //    log.info("Cadastrando game {}", gameXboxRecord);

       //  Xbox game = Xbox.builder()
         //        .titulo(gameXboxRecord.titulo())
          //       .preco(gameXboxRecord.preco())
          //       .descricao(gameXboxRecord.descricao())
           //      .build();

        // Xbox savedGame = xboxRepository.save(game);
        // return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    // }
    
    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Xbox> show(@PathVariable Long id) {
        log.info("buscando gameXbox com id {}", id);
        
        return xboxRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<XboxRecord> update(@PathVariable Long id, @RequestBody XboxRecord updatedGame) {
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

         for(XboxRecord gameXboxRecord : repository){
            if(gameXboxRecord.id().equals(id)){
                repository.remove(gameXboxRecord);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}