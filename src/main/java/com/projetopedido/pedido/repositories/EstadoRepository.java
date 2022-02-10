package com.projetopedido.pedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopedido.pedido.domain.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
