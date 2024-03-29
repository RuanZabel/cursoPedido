package com.projetopedido.pedido.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetopedido.pedido.domain.Cliente;
import com.projetopedido.pedido.dto.ClienteDTO;
import com.projetopedido.pedido.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {		
		Cliente obj = service.buscarCliente(id);
	
		return ResponseEntity.ok().body(obj);
	}
	
	//@RequestMapping(method = RequestMethod.POST)
	//public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO){ // @requestBody converte json automaticamente
	//	Cliente obj = service.fromDTO(objDTO);
	//	obj = service.insert(obj);
	//	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).build();
	//}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {	
		service.delete(id);		
		return ResponseEntity.noContent().build();		
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {		
		List<Cliente> lista = service.findAll();
		List<ClienteDTO> listaDto = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);	
	}	
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPages", defaultValue = "24")Integer linesPages,
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) 
		{		
		Page<Cliente> lista = service.findPage(page, linesPages,orderBy,direction);
		Page<ClienteDTO> listaDto = lista.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDto);	
	}		
	
}
