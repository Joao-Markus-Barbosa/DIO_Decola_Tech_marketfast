package com.marketfast.domain.cliente.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O email é obrigatório.") @Email(message = "O email deve ser válido.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email é obrigatório.") @Email(message = "O email deve ser válido.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha é obrigatória.") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha é obrigatória.") String senha) {
        this.senha = senha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


}