package com.projetopedido.pedido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopedido.pedido.domain.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
