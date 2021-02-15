package com.mayank.microservices.currencyexchangeservice.exchange;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchangeBean getCurrencyExchange(@PathVariable String from, 
			@PathVariable String to) {
		CurrencyExchangeBean bean = repository.findByFromAndTo(from, to);
		if (bean == null) {
			throw new RuntimeException("Unable to find the data");
		}
		bean.setEnvironment(environment.getProperty("local.server.port"));
		return bean;
	}
}
