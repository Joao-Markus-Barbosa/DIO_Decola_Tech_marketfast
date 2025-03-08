package com.marketfast.domain.pedido.service;

import com.marketfast.domain.pedido.model.Pedido;
import com.marketfast.domain.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Listar todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado com id: " + id));
    }

    // Salvar um novo pedido
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Deletar pedido por ID
    public boolean deletar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Atualizar um pedido existente
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado com id: " + id));

        // Atualiza os campos do pedido com os dados fornecidos
        pedido.setCliente(pedidoAtualizado.getCliente());
        pedido.setProdutos(pedidoAtualizado.getProdutos());
        pedido.setStatus(pedidoAtualizado.getStatus());
        pedido.setTotal(pedidoAtualizado.getTotal());
        pedido.setValorFrete(pedidoAtualizado.getValorFrete());

        // Salva e retorna o pedido atualizado
        return pedidoRepository.save(pedido);
    }
}
