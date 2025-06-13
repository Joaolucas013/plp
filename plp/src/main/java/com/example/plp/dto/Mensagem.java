package com.example.plp.dto;

import com.example.plp.dto.prod.TotalProdutos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




public class Mensagem {
   private String mensagem;
   TotalProdutos totalProdutos;

    public Mensagem(String msg, TotalProdutos totalProdutos) {
        this.mensagem = msg;
        this.totalProdutos = totalProdutos;
    }

    public Mensagem(){
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TotalProdutos getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(TotalProdutos totalProdutos) {
        this.totalProdutos = totalProdutos;
    }
}
