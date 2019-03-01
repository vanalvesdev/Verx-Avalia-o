package com.van.avaliacaoverx.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClimaDetalhadoVO {

	@JsonProperty("min_temp")
	private long minTemp;
	
	@JsonProperty("max_temp")
	private long maxTemp;
}
