package com.van.avaliacaoverx.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class IpVigilanteVO {

	private String status;
	private Data data;
	

	@Getter
	@Setter
	@ToString
	public class Data {
		
		@JsonProperty("city_name")
		private String cityName;
		
		private String latitude;
		private String longitude;
	}
}
