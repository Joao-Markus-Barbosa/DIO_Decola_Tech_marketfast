package com.marketfast.domain.pedido.controller;

import com.marketfast.domain.pedido.model.Pedido;
import com.marketfast.domain.pedido.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Listar todos os pedidos
    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos no sistema.")
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna o pedido com o ID fornecido.")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable @Parameter(description = "ID do pedido a ser buscado") Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Criar um novo pedido
    @PostMapping
    @Operation(summary = "Criar um novo pedido", description = "Recebe as informações de um novo pedido e o salva no sistema.")
    public ResponseEntity<Pedido> salvar(@RequestBody @Parameter(description = "Dados do pedido a ser criado") Pedido pedido) {
        Pedido pedidoSalvo = pedidoService.salvar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

    // Deletar pedido por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pedido por ID", description = "Exclui um pedido do sistema com o ID fornecido.")
    public ResponseEntity<Void> deletar(@PathVariable @Parameter(description = "ID do pedido a ser deletado") Long id) {
        boolean deleted = pedidoService.deletar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Atualizar pedido
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pedido", description = "Atualiza os dados de um pedido existente com o ID fornecido.")
    public ResponseEntity<Pedido> atualizar(@PathVariable @Parameter(description = "ID do pedido a ser atualizado") Long id,
                                            @RequestBody @Parameter(description = "Dados atualizados do pedido") Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizar(id, pedidoAtualizado);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
