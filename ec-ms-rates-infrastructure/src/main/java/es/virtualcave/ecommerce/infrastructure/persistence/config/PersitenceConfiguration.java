package es.virtualcave.ecommerce.infrastructure.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.virtualcave.ecommerce.infrastructure.persistence.jpa.adapter.RateRepositoryAdapter;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.mapper.RateDbEntityMapper;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.repository.RateRepository;

@Configuration
public class PersitenceConfiguration {

	@Bean
	public RateRepositoryAdapter rateRepositoryAdapter(final RateRepository rateRepository,
			final RateDbEntityMapper rateDbEntityMapper) {
		return new RateRepositoryAdapter(rateRepository, rateDbEntityMapper);
	}

}
