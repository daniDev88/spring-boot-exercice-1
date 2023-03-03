package es.virtualcave.ecommerce.infrastructure.currency.adapter;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.virtualcave.ecommerce.domain.model.Currency;
import es.virtualcave.ecommerce.domain.spi.CurrencyRepositoryPort;
import es.virtualcave.ecommerce.infrastructure.currency.CurrencyServiceMock;
import es.virtualcave.ecommerce.infrastructure.currency.mapper.CurrencyMapper;

public class CurrencyIntegrationAdapter implements CurrencyRepositoryPort {

	private final CurrencyServiceMock currencyMockService;

	private final CurrencyMapper currencyMapper;

	public CurrencyIntegrationAdapter(final CurrencyServiceMock currencyMockService,
			final CurrencyMapper currencyMapper) {
		this.currencyMockService = currencyMockService;
		this.currencyMapper = currencyMapper;
	}

	@Override
	public Optional<Currency> findByCode(String code) {
		return Optional.ofNullable(code).filter(StringUtils::isNotBlank).map(currencyMockService::getCurrencyByCode)
				.filter(e -> e.getStatusCode().equals(HttpStatus.OK)).map(ResponseEntity::getBody)
				.map(currencyMapper::asCurrency);
	}

}
