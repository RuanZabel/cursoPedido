package com.projetopedido.pedido;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetopedido.pedido.domain.Categoria;
import com.projetopedido.pedido.domain.Cidade;
import com.projetopedido.pedido.domain.Estado;
import com.projetopedido.pedido.domain.Produto;
import com.projetopedido.pedido.repositories.CategoriaRepository;
import com.projetopedido.pedido.repositories.CidadeRepository;
import com.projetopedido.pedido.repositories.EstadoRepository;
import com.projetopedido.pedido.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidoApplication implements CommandLineRunner{ // esse implementes é para iniciar iniciar alguma coisa
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;	
	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1=new Categoria(null, "informática");
		Categoria cat2=new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "PC", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		Estado est1=new Estado(null, "Minas Gerais");
		Estado est2=new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Ilhota", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

}
