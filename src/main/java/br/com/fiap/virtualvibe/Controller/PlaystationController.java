package br.com.fiap.virtualvibe.controller;

import java.util.List;
import java.util.Optional;

import br.com.fiap.virtualvibe.model.Playstation;
import br.com.fiap.virtualvibe.record.PlaystationRecord;
import br.com.fiap.virtualvibe.repository.PlaystationRepository;
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

@RestController
@RequestMapping("ps-games")
public class PlaystationController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PlaystationRepository playstationRepository;

    @GetMapping
    public List<Playstation> index() {
        log.info("[PLAYSTATION] Buscando todos os jogos");

        return playstationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Playstation> create(@RequestBody PlaystationRecord gamePlaystationRecord) { //binding
        log.info("[PLAYSTATION] Cadastrando jogo {}", gamePlaystationRecord);

        Playstation game = Playstation.builder()
                .titulo(gamePlaystationRecord.titulo())
                .preco(gamePlaystationRecord.preco())
                .descricao(gamePlaystationRecord.descricao())
                .build();

        Playstation savedGame = playstationRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @GetMapping("{id}")
    public ResponseEntity<Playstation> show(@PathVariable Long id) {
        log.info("[PLAYSTATION] Buscando game com id {}", id);

        Optional<Playstation> gameOpt = playstationRepository.findById(id);

        if (gameOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(gameOpt.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Playstation> update(@PathVariable Long id, @RequestBody PlaystationRecord updatedGame) {
        log.info("[PLAYSTATION] Atualizando game com id {}", id);

        Optional<Playstation> gameOpt = playstationRepository.findById(id);

        if (gameOpt.isPresent()) {

            Playstation playstation = gameOpt.get();
            playstation.setTitulo(updatedGame.titulo());
            playstation.setPreco(updatedGame.preco());
            playstation.setDescricao(updatedGame.descricao());

            return ResponseEntity.status(HttpStatus.OK).body(playstationRepository.save(playstation));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("[PLAYSTATION] Deletando game com id {}", id);

        Optional<Playstation> playstationGame = playstationRepository.findById(id);

        if (playstationGame.isPresent()) {
            playstationRepository.delete(playstationGame.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}   