package com.van.avaliacaoverx.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteIp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ip;
	private String cidade;
	private Long maxTemperatura;
	private Long minTemperatura;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
}
