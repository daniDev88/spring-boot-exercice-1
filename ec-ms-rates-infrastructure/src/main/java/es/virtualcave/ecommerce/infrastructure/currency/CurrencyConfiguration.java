package es.virtualcave.ecommerce.infrastructure.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.virtualcave.ecommerce.infrastructure.currency.adapter.CurrencyIntegrationAdapter;
import es.virtualcave.ecommerce.infrastructure.currency.mapper.CurrencyMapper;

@Configuration
public class CurrencyConfiguration {

	@Bean
	public CurrencyServiceMock currencyRestMockService() {
		return new CurrencyServiceMock();
	}

	@Bean
	public CurrencyIntegrationAdapter currencyIntegrationAdapter(final CurrencyServiceMock currencyServiceMock,
			final CurrencyMapper currencyMapper) {
		return new CurrencyIntegrationAdapter(currencyServiceMock, currencyMapper);
	}
}
