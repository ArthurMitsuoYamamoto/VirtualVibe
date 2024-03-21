package br.com.fiap.virtualvibe.controller;

import java.util.List;

import br.com.fiap.virtualvibe.model.Nintendo;
import br.com.fiap.virtualvibe.repository.NintendoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("nintendo-games")
@Slf4j
public class NintendoController {
    
    @Autowired
    NintendoRepository nintendoRepository;

    @GetMapping
    public List<Nintendo> index() {
        log.info("[NINTENDO] Buscando todos os jogos");
        return nintendoRepository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(CREATED)
    public Nintendo create(@RequestBody @Valid Nintendo gameNintendo) { //binding
        log.info("[NINTENDO] Cadastrando jogo {}", gameNintendo);
        return nintendoRepository.save(gameNintendo);
    }

    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Nintendo> show(@PathVariable Long id) {
        log.info("[NINTENDO] Buscando game com id {}", id);

        return nintendoRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @PutMapping("{id}")
    public Nintendo update(@PathVariable Long id, @RequestBody Nintendo updatedGame) {
        log.info("[NINTENDO] Atualizando game com id {}", id);
        verificarSeJogoExiste(id);
        return nintendoRepository.save(updatedGame);
    }

    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("[NINTENDO] Deletando game com id {}", id);
        verificarSeJogoExiste(id);
        nintendoRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    private void verificarSeJogoExiste(Long id) {
        nintendoRepository
            .findById(id)
            .orElseThrow(()-> new ResponseStatusException(
                    NOT_FOUND, "Não existe jogo com o id informado."
            ));
    }
}