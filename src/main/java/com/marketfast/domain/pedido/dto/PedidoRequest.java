package com.marketfast.domain.pedido.dto;


import java.util.List;

public class PedidoRequest {
    private Long clienteId;
    private List<Long> produtoIds;
    private Double total;
    private Double valorFrete;

    // Getters e Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<Long> getProdutoIds() { return produtoIds; }
    public void setProdutoIds(List<Long> produtoIds) { this.produtoIds = produtoIds; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Double getValorFrete() { return valorFrete; }
    public void setValorFrete(Double valorFrete) { this.valorFrete = valorFrete; }
}

