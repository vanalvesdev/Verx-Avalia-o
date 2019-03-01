package com.van.avaliacaoverx.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.van.avaliacaoverx.model.Cliente;
import com.van.avaliacaoverx.model.ClienteIp;
import com.van.avaliacaoverx.repository.ClienteIpRepository;
import com.van.avaliacaoverx.repository.ClienteRepository;
import com.van.avaliacaoverx.vo.ClimaDetalhadoVO;
import com.van.avaliacaoverx.vo.ClimaVO;
import com.van.avaliacaoverx.vo.IpVigilanteVO;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteIpRepository clientIprepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String CLIMA_URL = "https://www.metaweather.com/api/location/search/?lattlong={lattlong}";
	private static final String CLIMA_DETALHADO_URL = "https://www.metaweather.com/api/location/{woeid}/{data}";
	private static final String IP_URL = "https://ipvigilante.com/json/{ip}";
	
	private static final String CLIMA_DATE_FORMAT = "yyyy/MM/dd";
	
	public Cliente save(Cliente cliente, String ip) {
		
		Cliente clientSalvo = repository.save(cliente);
		ClienteIp clienteIp = obterInformacoesClima(ip, clientSalvo);
		clientIprepository.save(clienteIp);
		return clientSalvo;
	}
	
	@CacheEvict(value = "cliente", key="#id")
	public void remove(Long id) {
		Cliente cliente = find(id);
		ClienteIp clienteIp = clientIprepository.findByClienteId(id);
		clientIprepository.delete(clienteIp);
		repository.delete(cliente);
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
	
	private ClienteIp obterInformacoesClima(String ip, Cliente cliente) {

		IpVigilanteVO ipVigilanteVO = restTemplate.getForObject(IP_URL, IpVigilanteVO.class, ip);
		String latitude = ipVigilanteVO.getData().getLatitude();
		String longitude = ipVigilanteVO.getData().getLongitude();
		
		List<ClimaVO> listClima = Arrays.asList(restTemplate.getForObject(CLIMA_URL, ClimaVO[].class, latitude.concat(",").concat(longitude)));
		ClimaVO clima = listClima.stream()
				.filter(c-> c.getTitle().equals(ipVigilanteVO.getData().getCityName()))
				.findFirst()
				.orElse(listClima.get(0));
		
		String data = LocalDate.now().format(DateTimeFormatter.ofPattern(CLIMA_DATE_FORMAT));
		
		ClimaDetalhadoVO climaDetalhadoVO = restTemplate.getForObject(CLIMA_DETALHADO_URL, ClimaDetalhadoVO[].class, clima.getWoeid(), data)[0];
		
		return ClienteIp.builder()
						.cidade(clima.getTitle())
						.maxTemperatura(climaDetalhadoVO.getMaxTemp())
						.minTemperatura(climaDetalhadoVO.getMinTemp())
						.ip(ip)
						.cliente(cliente)
						.build();
	}
}
