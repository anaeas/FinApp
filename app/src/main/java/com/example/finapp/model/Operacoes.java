package com.example.finapp.model;

import java.io.Serializable;

public class Operacoes implements Serializable {
    private Long id;
    private String tipo;
    private String categoria;
    private double valor;
    private String data;
    
    public Operacoes(String tipo, String categoria, double valor, String data )
    {
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setCValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return ("Operacao: " + tipo + " Categoria: " + categoria + " Valor: " + valor + " Data: " + data);
    }
}


