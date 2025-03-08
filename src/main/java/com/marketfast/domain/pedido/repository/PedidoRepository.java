package com.marketfast.domain.pedido.repository;



import com.marketfast.domain.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

