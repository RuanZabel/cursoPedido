package com.projetopedido.pedido;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetopedido.pedido.domain.Categoria;
import com.projetopedido.pedido.repositories.CategoriaRepository;

@SpringBootApplication
public class PedidoApplication implements CommandLineRunner{ // esse implementes é para iniciar iniciar alguma coisa
	@Autowired
	private CategoriaRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1=new Categoria(null, "informática");
		Categoria cat2=new Categoria(null, "escritorio");
		repository.saveAll(Arrays.asList(cat1,cat2));
	}

}
