package com.mayank.microservices.currencyexchangeservice.exchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeBean, Integer> {

	CurrencyExchangeBean findByFromAndTo(String from, String to);
}
