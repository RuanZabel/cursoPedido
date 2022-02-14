package com.projetopedido.pedido.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopedido.pedido.domain.Pedido;
import com.projetopedido.pedido.repositories.PedidoRepository;

@Service
public class PedidoService {
	//dependencia automaticamente instanciada pelo o spring
	@Autowired
	private PedidoRepository repo;
	public Pedido buscarPedido(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.projetopedido.pedido.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ID:"+id+" Tipo "+Pedido.class.getName()));
		
		//Optional<Pedido>obj=repo.findById(id);
		//return obj.orElse(null);
	}
}
