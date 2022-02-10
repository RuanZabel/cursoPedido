package com.projetopedido.pedido.services.exceptions;

//classe padrão para exceção

public class ObjectNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	public ObjectNotFoundException (String msg,Throwable causa) {
		super(msg,causa);
	}
	
}
