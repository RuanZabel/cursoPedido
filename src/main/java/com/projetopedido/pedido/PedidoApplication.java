package com.projetopedido.pedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetopedido.pedido.domain.Categoria;
import com.projetopedido.pedido.domain.Cidade;
import com.projetopedido.pedido.domain.Cliente;
import com.projetopedido.pedido.domain.Endereco;
import com.projetopedido.pedido.domain.Estado;
import com.projetopedido.pedido.domain.ItemPedido;
import com.projetopedido.pedido.domain.Pagamento;
import com.projetopedido.pedido.domain.PagamentoComBoleto;
import com.projetopedido.pedido.domain.PagamentoComCartao;
import com.projetopedido.pedido.domain.Pedido;
import com.projetopedido.pedido.domain.Produto;
import com.projetopedido.pedido.domain.enums.EstadoPagamento;
import com.projetopedido.pedido.domain.enums.TipoCliente;
import com.projetopedido.pedido.repositories.CategoriaRepository;
import com.projetopedido.pedido.repositories.CidadeRepository;
import com.projetopedido.pedido.repositories.ClienteRepository;
import com.projetopedido.pedido.repositories.EnderecoRepository;
import com.projetopedido.pedido.repositories.EstadoRepository;
import com.projetopedido.pedido.repositories.ItemPedidoRepository;
import com.projetopedido.pedido.repositories.PagamentoRepository;
import com.projetopedido.pedido.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1=new Categoria(null, "informática");
		Categoria cat2=new Categoria(null, "escritorio");
		Categoria cat3=new Categoria(null, "cat3");
		Categoria cat4=new Categoria(null, "cat4");
		Categoria cat5=new Categoria(null, "cat5");
		Categoria cat6=new Categoria(null, "cat6");
		Categoria cat7=new Categoria(null, "cat7");
		
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
		
		Cliente cl1 = new Cliente(null, "Maria", "maria@gmail.com", "123,1456", TipoCliente.PESSOAFISICA);
		cl1.getTelefones().addAll(Arrays.asList("123456","1234567"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "3232325555", cl1, c1);
		Endereco e2 = new Endereco(null, "Avenida ", "301", "Apto 305", "Jardim", "3232325555", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
