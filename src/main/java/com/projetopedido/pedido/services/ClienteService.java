package com.projetopedido.pedido.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopedido.pedido.domain.Cliente;
import com.projetopedido.pedido.repositories.ClienteRepository;

@Service
public class ClienteService {
	//dependencia automaticamente instanciada pelo o spring
	@Autowired
	private ClienteRepository repo;
	public Cliente buscarCliente(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.projetopedido.pedido.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ID:"+id+" Tipo "+Cliente.class.getName()));
		
		//Optional<Cliente>obj=repo.findById(id);
		//return obj.orElse(null);
	}
}
