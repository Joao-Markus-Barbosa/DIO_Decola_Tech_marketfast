package com.marketfast.domain.cliente.dto;



public class ClienteResponse {
    private Long id;
    private String nome;
    private String email;
    private String categoria;

    // Construtor
    public ClienteResponse(Long id, String nome, String email, String categoria) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.categoria = categoria;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCategoria() {
        return categoria;
    }
}
