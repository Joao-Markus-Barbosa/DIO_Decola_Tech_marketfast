package com.marketfast.domain.cliente.controller;

import com.marketfast.domain.cliente.dto.ClienteRequest;
import com.marketfast.domain.cliente.dto.ClienteResponse;
import com.marketfast.domain.cliente.model.Cliente;
import com.marketfast.domain.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar um novo cliente", description = "Recebe as informações de um novo cliente e o cadastra no sistema.")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Parameter(description = "Dados do cliente a ser cadastrado") ClienteRequest clienteRequest) {
        ClienteResponse response = clienteService.cadastrarCliente(clienteRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados no sistema.")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar os dados de um cliente", description = "Atualiza as informações de um cliente existente com o ID fornecido.")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable @Parameter(description = "ID do cliente a ser atualizado") Long id,
            @RequestBody @Parameter(description = "Novos dados do cliente") ClienteRequest clienteRequest) {
        ClienteResponse response = clienteService.atualizarCliente(id, clienteRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um cliente", description = "Exclui um cliente do sistema com base no ID fornecido.")
    public ResponseEntity<Void> excluirCliente(@PathVariable @Parameter(description = "ID do cliente a ser excluído") Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Novo endpoint para buscar cliente por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna os dados de um cliente com base no ID fornecido.")
    public ResponseEntity<ClienteResponse> buscarClientePorId(
            @PathVariable @Parameter(description = "ID do cliente a ser buscado") Long id) {
        ClienteResponse response = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(response);
    }
}