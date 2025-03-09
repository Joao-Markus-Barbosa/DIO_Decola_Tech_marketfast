package com.marketfast.domain.produto.controller;

import com.marketfast.domain.produto.model.Produto;
import com.marketfast.domain.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os produtos
    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados no sistema.")
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna o produto com o ID fornecido.")
    public ResponseEntity<Produto> buscarPorId(@PathVariable @Parameter(description = "ID do produto a ser buscado") Long id) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            return ResponseEntity.ok(produto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Criar um novo produto
    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Recebe as informações de um novo produto e o salva no sistema.")
    public ResponseEntity<Produto> salvar(@Valid @RequestBody @Parameter(description = "Dados do produto a ser criado") Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    // Atualizar produto por ID
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto por ID", description = "Atualiza as informações de um produto existente com o ID fornecido.")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable @Parameter(description = "ID do produto a ser atualizado") Long id,
            @Valid @RequestBody @Parameter(description = "Novos dados do produto") Produto produtoAtualizado) {
        try {
            Produto produto = produtoService.atualizar(id, produtoAtualizado);
            return ResponseEntity.ok(produto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Deletar produto por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto por ID", description = "Exclui um produto do sistema com o ID fornecido.")
    public ResponseEntity<Void> deletar(@PathVariable @Parameter(description = "ID do produto a ser deletado") Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}