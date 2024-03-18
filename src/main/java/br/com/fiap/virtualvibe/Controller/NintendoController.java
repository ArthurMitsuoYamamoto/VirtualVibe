package br.com.fiap.virtualvibe.controller;

import java.util.List;
import java.util.Optional;

import br.com.fiap.virtualvibe.model.Nintendo;
import br.com.fiap.virtualvibe.repository.NintendoRepository;
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

import br.com.fiap.virtualvibe.record.NintendoRecord;


@RestController
@RequestMapping("nintendo-games")
public class NintendoController {
    
     Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    NintendoRepository nintendoRepository;

    @GetMapping
    public List<Nintendo> index() {
        log.info("[NINTENDO] Buscando todos os jogos");

        return nintendoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Nintendo> create(@RequestBody NintendoRecord gameNintendoRecord) { //binding
        log.info("[NINTENDO] Cadastrando jogo {}", gameNintendoRecord);

        Nintendo game = Nintendo.builder()
                .titulo(gameNintendoRecord.titulo())
                .preco(gameNintendoRecord.preco())
                .descricao(gameNintendoRecord.descricao())
                .build();

        Nintendo savedGame = nintendoRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @GetMapping("{id}")
    public ResponseEntity<Nintendo> show(@PathVariable Long id) {
        log.info("[NINTENDO] Buscando game com id {}", id);

        Optional<Nintendo> gameOpt = nintendoRepository.findById(id);

        if (gameOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(gameOpt.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Nintendo> update(@PathVariable Long id, @RequestBody NintendoRecord updatedGame) {
        log.info("[NINTENDO] Atualizando game com id {}", id);

        Optional<Nintendo> gameOpt = nintendoRepository.findById(id);

        if (gameOpt.isPresent()) {

            Nintendo nintendo = gameOpt.get();
            nintendo.setTitulo(updatedGame.titulo());
            nintendo.setPreco(updatedGame.preco());
            nintendo.setDescricao(updatedGame.descricao());

            return ResponseEntity.status(HttpStatus.OK).body(nintendoRepository.save(nintendo));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("[NINTENDO] Deletando game com id {}", id);

        Optional<Nintendo> nintendoGame = nintendoRepository.findById(id);

        if (nintendoGame.isPresent()) {
            nintendoRepository.delete(nintendoGame.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}