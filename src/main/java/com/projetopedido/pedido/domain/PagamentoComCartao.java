package com.projetopedido.pedido.domain;

import javax.persistence.Entity;

import com.projetopedido.pedido.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;



	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas=numeroParcelas;
	}



	public PagamentoComCartao() {
		
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}



	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
}
