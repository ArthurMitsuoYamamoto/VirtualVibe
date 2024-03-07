package br.com.fiap.virtualvibe.record;

import java.util.Random;

public record Nintendo(Long id, String titulo, Number preco, String descricao) {
    public Nintendo(Long id, String titulo, Number preco, String descricao){
       this.id  = Math.abs (new Random().nextLong());
       this.titulo = titulo;
       this.preco = preco;
       this.descricao = descricao;
   }
}


//Imutável - record