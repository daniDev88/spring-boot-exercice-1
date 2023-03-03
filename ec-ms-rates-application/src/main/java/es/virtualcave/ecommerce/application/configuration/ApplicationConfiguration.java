package es.virtualcave.ecommerce.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.virtualcave.ecommerce.domain.api.DomainRateServicePort;
import es.virtualcave.ecommerce.domain.api.DomainRateServicePortImpl;
import es.virtualcave.ecommerce.domain.spi.CurrencyRepositoryPort;
import es.virtualcave.ecommerce.domain.spi.RateRepositoryPort;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url("http://localhost:8080/").description("Application Local Server URL"));
	}

	@Bean
	public DomainRateServicePort domainRateServicePort(final RateRepositoryPort rateRepositoryPort,
			final CurrencyRepositoryPort currencyRepositoryPort) {
		return new DomainRateServicePortImpl(rateRepositoryPort, currencyRepositoryPort);
	}

}
