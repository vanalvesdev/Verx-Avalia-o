package com.van.avaliacaoverx.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.van.avaliacaoverx.model.Cliente;
import com.van.avaliacaoverx.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value="Retorna uma lista de clientes")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> buscarTodosClientes() {
		return clienteService.all();
	}

	@ApiOperation(value="Salva um cliente no banco de dados")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente criarCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@ApiOperation(value="Atualiza as informações de um cliente existente")
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente atualizarCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		return clienteService.update(cliente, id);
	}
	
	@ApiOperation(value="Remove um cliente do banco de dados")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Long id) {
		clienteService.remove(id);
	}
	
	@ApiOperation(value="Recupera as informações de um cliente especifico")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarCliente(@PathVariable Long id) {
		return clienteService.find(id);
	}
	
}
