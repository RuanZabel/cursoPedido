package com.projetopedido.pedido.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopedido.pedido.domain.Categoria;
import com.projetopedido.pedido.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	//dependencia automaticamente instanciada pelo o spring
	@Autowired
	private CategoriaRepository repo;
	public Categoria buscarCategoria(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.projetopedido.pedido.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado ID:"+id+" Tipo "+Categoria.class.getName()));
		
		//Optional<Categoria>obj=repo.findById(id);
		//return obj.orElse(null);
	}
}
