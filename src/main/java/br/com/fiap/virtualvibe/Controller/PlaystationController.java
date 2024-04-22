package br.com.fiap.virtualvibe.Controller;

import java.util.List;

import br.com.fiap.virtualvibe.model.Playstation;
import br.com.fiap.virtualvibe.repository.PlaystationRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("ps-games")
@Slf4j
public class PlaystationController {
    
    @Autowired
    PlaystationRepository playstationRepository;

    @GetMapping
    public List<Playstation> index() {
        log.info("[PLAYSTATION] Buscando todos os jogos");
        return playstationRepository.findAll();
    }

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(CREATED)
    public Playstation create(@RequestBody @Valid Playstation gamePlaystation) { //binding
        log.info("[PLAYSTATION] Cadastrando jogo {}", gamePlaystation);
        return playstationRepository.save(gamePlaystation);
    }

    @SuppressWarnings("null")
    @GetMapping("{id}")
    public ResponseEntity<Playstation> show(@PathVariable Long id) {
        log.info("[PLAYSTATION] Buscando game com id {}", id);

        return playstationRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("null")
    @PutMapping("{id}")
    public Playstation update(@PathVariable Long id, @RequestBody Playstation updatedGame) {
        log.info("[PLAYSTATION] Atualizando game com id {}", id);
        verificarSeJogoExiste(id);
        return playstationRepository.save(updatedGame);
    }

    @SuppressWarnings("null")
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("[PLAYSTATION] Deletando game com id {}", id);
        verificarSeJogoExiste(id);
        playstationRepository.deleteById(id);
    }

    @SuppressWarnings("null")
    private void verificarSeJogoExiste(Long id) {
        playstationRepository
            .findById(id)
            .orElseThrow(()-> new ResponseStatusException(
                    NOT_FOUND, "Não existe jogo com o id informado."
            ));
    }
}