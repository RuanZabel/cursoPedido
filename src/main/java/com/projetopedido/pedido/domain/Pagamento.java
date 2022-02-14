package com.projetopedido.pedido.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.projetopedido.pedido.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // isso é usado para separar as tabelas pagamentoComBoleto e pagamentoComCartao, poderia fazer uma unica tabela usando o outro type 
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id // vai ter o mesmo id da tabela pedido, o @MapsId é para isso
	private Integer id;
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId  
	private Pedido pedido;

	public Pagamento() {
		super();
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
}
