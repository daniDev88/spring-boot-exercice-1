package es.virtualcave.ecommerce.infrastructure.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConfiguration {

	@Bean
	public CurrencyRestMockServiceImpl currencyRestMockService() {
		return new CurrencyRestMockServiceImpl();
	}

}
