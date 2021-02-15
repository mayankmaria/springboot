package com.mayank.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from,
			@PathVariable String to, @PathVariable int quantity) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("from", from);
		map.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = 
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, map);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity, 
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)), currencyConversion.getEnvironment());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyViaFeign(@PathVariable String from,
			@PathVariable String to, @PathVariable int quantity) {
		CurrencyConversion currencyConversion = proxy.getCurrencyExchange(from, to);
		return new CurrencyConversion(currencyConversion.getId(), from, to,
				currencyConversion.getConversionMultiple(), quantity, 
				currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)), currencyConversion.getEnvironment()+"-feign");
	}
		
}
