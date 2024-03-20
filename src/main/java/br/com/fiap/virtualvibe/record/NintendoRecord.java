package br.com.fiap.virtualvibe.record;

import java.util.Random;

public record NintendoRecord(Long id, String titulo, Double preco, String descricao) {
    public NintendoRecord(Long id, String titulo, Double preco, String descricao){
       this.id  = (id == null)? Math.abs (new Random().nextLong()) : id;
       this.titulo = titulo;
       this.preco = preco;
       this.descricao = descricao;
   }
}


//Imutável - record