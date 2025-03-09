package com.marketfast.domain.cliente.service;

import com.marketfast.domain.cliente.dto.ClienteRequest;
import com.marketfast.domain.cliente.dto.ClienteResponse;
import com.marketfast.domain.cliente.model.Cliente;
import com.marketfast.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest) {
        if (clienteRequest.getNome() == null || clienteRequest.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
        if (clienteRequest.getEmail() == null || clienteRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email do cliente é obrigatório.");
        }
        if (clienteRequest.getSenha() == null || clienteRequest.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha do cliente é obrigatória.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setSenha(clienteRequest.getSenha());
        cliente.setCategoria(clienteRequest.getCategoria());

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteResponse(clienteSalvo.getId(), clienteSalvo.getNome(), clienteSalvo.getEmail(), clienteSalvo.getCategoria().toString());
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public ClienteResponse atualizarCliente(Long id, ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteRequest.getNome());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setSenha(clienteRequest.getSenha());
        cliente.setCategoria(clienteRequest.getCategoria());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ClienteResponse(clienteAtualizado.getId(), clienteAtualizado.getNome(), clienteAtualizado.getEmail(), clienteAtualizado.getCategoria().toString());
    }

    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    // Método para buscar cliente por ID
    public ClienteResponse buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCategoria().toString());
    }
}