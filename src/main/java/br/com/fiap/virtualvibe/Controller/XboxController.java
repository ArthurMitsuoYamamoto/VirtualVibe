package br.com.fiap.virtualvibe.controller;

import java.util.List;

import br.com.fiap.virtualvibe.model.Xbox;
import br.com.fiap.virtualvibe.repository.XboxRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("xbox-games")
@Slf4j
public class XboxController {
    
    @Autowired
    XboxRepository xboxRepository;

    @GetMapping
    public List<Xbox> index() {
        log.info("[Xbox] Buscando todos os jogos");
        return xboxRepository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(CREATED)
    public Xbox create(@RequestBody @Valid Xbox gameXbox) { //binding
        log.info("[Xbox] Cadastrando jogo {}", gameXbox);
        return xboxRepository.save(gameXbox);
    }

    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Xbox> show(@PathVariable Long id) {
        log.info("[Xbox] Buscando game com id {}", id);

        return xboxRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @PutMapping("{id}")
    public Xbox update(@PathVariable Long id, @RequestBody Xbox updatedGame) {
        log.info("[Xbox] Atualizando game com id {}", id);
        verificarSeJogoExiste(id);
        return xboxRepository.save(updatedGame);
    }

    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("[Xbox] Deletando game com id {}", id);
        verificarSeJogoExiste(id);
        xboxRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    private void verificarSeJogoExiste(Long id) {
        xboxRepository
            .findById(id)
            .orElseThrow(()-> new ResponseStatusException(
                    NOT_FOUND, "Não existe jogo com o id informado."
            ));
    }
}