package br.com.fiap.virtualvibe.model;

import java.util.Random;

public record Playstation(Long id, String titulo, Number preco, String descricao) {
     public Playstation(Long id, String titulo, Number preco, String descricao){
        this.id  = Math.abs (new Random().nextLong());
        this.titulo = titulo;
        this.preco = preco;
        this.descricao = descricao;
    }
}
    
//imutável - record

    

