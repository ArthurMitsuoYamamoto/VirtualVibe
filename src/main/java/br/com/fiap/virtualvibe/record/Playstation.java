package br.com.fiap.virtualvibe.record;

import java.util.Random;

public record Playstation(Long id, String titulo, Number preco, String descricao) {
     public Playstation(Long id, String titulo, Number preco, String descricao){
        this.id  = (id == null)? Math.abs (new Random().nextLong()) : id;
        this.titulo = titulo;
        this.preco = preco;
        this.descricao = descricao;
    }
}
    
//Imutável - record

    

