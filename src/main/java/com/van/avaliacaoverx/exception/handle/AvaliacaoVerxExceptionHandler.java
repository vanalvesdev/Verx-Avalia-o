package com.van.avaliacaoverx.exception.handle;

import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(5)
public class AvaliacaoVerxExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity handleEmptyResultDataAccessException() {
		return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
	}
}
