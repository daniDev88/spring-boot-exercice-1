package es.virtualcave.ecommerce.infrastructure.currency;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import es.virtualcave.infrastructure.currency.rest.api.CurrenciesApi;
import es.virtualcave.infrastructure.currency.rest.model.CurrencyDto;

public class CurrencyRestMockServiceImpl implements CurrenciesApi {

	private static final Map<String, CurrencyDto> CURRENCIES = Map.of("EUR", new CurrencyDto().code("EUR").symbol("€").decimals(2),
			"USD", new CurrencyDto().code("USD").symbol("$").decimals(4));

	@Override
	public ResponseEntity<CurrencyDto> getCurrencyByCode(String currencyCode) {
		Optional<CurrencyDto> optionalCurrency = Optional.ofNullable(CURRENCIES.get(currencyCode));
		return optionalCurrency.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
