package com.van.avaliacaoverx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.van.avaliacaoverx.model.Cliente;
import com.van.avaliacaoverx.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public List<Cliente> all() {
		return repository.findAll();
	}
	
	@Cacheable
	public Cliente find(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}
