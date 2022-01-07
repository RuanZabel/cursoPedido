package com.projetopedido.pedido.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetopedido.pedido.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@RequestMapping(method = RequestMethod.GET)
	public List<?> listar() {		
		Categoria categoria=new Categoria(1, "teste");
		Categoria categoria2=new Categoria(2, "teste2");
		List<Categoria> lista = new ArrayList<>();
		lista.add(categoria);
		lista.add(categoria2);
		return lista;
	}
	
}
