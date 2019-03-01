package com.van.avaliacaoverx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.van.avaliacaoverx.model.ClienteIp;

public interface ClienteIpRepository extends JpaRepository<ClienteIp, Long>{
	
	ClienteIp findByClienteId(Long clienteId);
}
