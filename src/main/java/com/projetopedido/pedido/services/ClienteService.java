package com.projetopedido.pedido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetopedido.pedido.domain.Cliente;
import com.projetopedido.pedido.dto.ClienteDTO;
import com.projetopedido.pedido.repositories.ClienteRepository;
import com.projetopedido.pedido.services.exceptions.DataIntegrityException;

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
	public Cliente update(Cliente obj) {
		Cliente newObj = buscarCliente(obj.getId());
		updateDate(newObj,obj);
		return repo.save(newObj);
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	public void delete(Integer id) {
		buscarCliente(id);
		try {
			repo.deleteById(id);	
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possivel excluir, pois, tem pedidos");
		}
		
	}
	public List<Cliente> findAll() {		
		return repo.findAll();
	}
	public Page<Cliente> findPage(Integer page, Integer linesPages,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPages,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}	
}
