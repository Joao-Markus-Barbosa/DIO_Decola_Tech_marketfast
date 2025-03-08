package com.marketfast.domain.pedido.model;

import com.marketfast.domain.cliente.model.Categoria;  // Importando a enum Categoria
import com.marketfast.domain.cliente.model.Cliente;
import com.marketfast.domain.produto.model.Produto;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "TB_PEDIDO")
public class Pedido {

    public enum StatusPedido {
        PENDENTE, PAGO, ENVIADO, ENTREGUE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    private Double total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private Double valorFrete;

    // Lógica para calcular o valor do frete com base no tipo de cliente
    public void calcularValorFrete() {
        if (this.cliente != null && this.cliente.getCategoria() == Categoria.PREMIUM) {
            this.valorFrete = 0.0; // Frete grátis para clientes PREMIUM
        } else {
            // Aqui você pode aplicar a lógica para calcular o valor do frete
            this.valorFrete = 10.0; // Exemplo: frete de 10.0 para clientes comuns
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public Double getValorFrete() { return valorFrete; }
    public void setValorFrete(Double valorFrete) { this.valorFrete = valorFrete; }
}

