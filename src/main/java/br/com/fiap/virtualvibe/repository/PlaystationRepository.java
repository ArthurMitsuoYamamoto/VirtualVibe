package br.com.fiap.virtualvibe.repository;

import br.com.fiap.virtualvibe.model.Playstation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaystationRepository extends JpaRepository<Playstation, Long> {
}
