package com.van.avaliacaoverx.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
	
	@CacheEvict(value = "cliente", key="#id")
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public List<Cliente> all() {
		return repository.findAll();
	}
	
	@Cacheable(value="cliente", unless="#result==null", key="#id")
	public Cliente find(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@CachePut(value = "cliente", key="#id")
	public Cliente update(Cliente cliente, Long id) {
		Cliente clienteSalvo = find(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return repository.save(clienteSalvo);
	}
}
