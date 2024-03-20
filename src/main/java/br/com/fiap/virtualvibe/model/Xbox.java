package br.com.fiap.virtualvibe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Xbox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{game.titulo.notblank}")
    @Size(min = 3, max = 35)
    private String titulo;

    @Positive(message = "{game.preco.positive}")
    private Double preco;

    
    @NotBlank(message = "{game.descricao.notblank}")
    @Size(min = 3, max = 255)
    private String descricao;


}