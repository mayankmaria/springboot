package com.mayank.microservices.currencyexchangeservice.exchange;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrencyExchangeBean {

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	@Id
	private int id;
	
	@Column(name = "currency_from")
	private String from;
	
	@Column(name = "currency_to")
	private String to;
	private BigDecimal conversionMultiple;
	private String environment;

	public int getId() {
		return id;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public CurrencyExchangeBean(int id, String from, String to, BigDecimal conversionMultiple, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.environment = environment;
	}

	public CurrencyExchangeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
