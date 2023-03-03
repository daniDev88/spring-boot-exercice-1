package es.virtualcave.ecommerce.domain.spi;

import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Currency;

public interface CurrencyRepositoryPort {

	Optional<Currency> findByCode(String code);

}
