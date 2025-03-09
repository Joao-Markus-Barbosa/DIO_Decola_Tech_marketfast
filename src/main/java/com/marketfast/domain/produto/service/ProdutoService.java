package com.marketfast.domain.produto.service;

import com.marketfast.domain.produto.model.Produto;
import com.marketfast.domain.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com id: " + id));
    }

    // Salvar um novo produto
    public Produto salvar(Produto produto) {
        // Validando se o produto tem informações mínimas válidas
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new ValidationException("O nome do produto é obrigatório.");
        }
        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new ValidationException("O preço do produto deve ser maior que zero.");
        }

        return produtoRepository.save(produto);
    }

    // Deletar produto por ID
    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com id: " + id));
        produtoRepository.delete(produto);
    }

    // Atualizar produto por ID
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        // Busca o produto existente no banco de dados
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com id: " + id));

        // Validando se o nome e o preço estão corretos
        if (produtoAtualizado.getNome() == null || produtoAtualizado.getNome().isEmpty()) {
            throw new ValidationException("O nome do produto não pode ser vazio.");
        }
        if (produtoAtualizado.getPreco() == null || produtoAtualizado.getPreco() <= 0) {
            throw new ValidationException("O preço do produto deve ser maior que zero.");
        }

        // Atualiza os campos do produto com os dados fornecidos
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());

        // Salva e retorna o produto atualizado
        return produtoRepository.save(produto);
    }
}