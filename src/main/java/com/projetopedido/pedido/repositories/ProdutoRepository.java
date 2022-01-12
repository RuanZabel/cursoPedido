package com.projetopedido.pedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopedido.pedido.domain.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
