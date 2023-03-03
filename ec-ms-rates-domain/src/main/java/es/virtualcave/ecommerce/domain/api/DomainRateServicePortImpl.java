package es.virtualcave.ecommerce.domain.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Rate;
import es.virtualcave.ecommerce.domain.spi.CurrencyRepositoryPort;
import es.virtualcave.ecommerce.domain.spi.RateRepositoryPort;

public class DomainRateServicePortImpl implements DomainRateServicePort {

	private final RateRepositoryPort rateRepositoryPort;

	private final CurrencyRepositoryPort currencyRepositoryPort;

	public DomainRateServicePortImpl(final RateRepositoryPort rateRepositoryPort,
			final CurrencyRepositoryPort currencyRepositoryPort) {
		this.rateRepositoryPort = rateRepositoryPort;
		this.currencyRepositoryPort = currencyRepositoryPort;
	}

	@Override
	public Optional<Rate> create(Rate rate) {
		return rateRepositoryPort.create(rate).map(this::updateRateCurrency);
	}

	@Override
	public Optional<Rate> findById(Long id) {
		return rateRepositoryPort.findById(id).map(this::updateRateCurrency);
	}

	@Override
	public void updatePrice(Long id, BigDecimal price) {
		rateRepositoryPort.updatePrice(id, price);
	}

	@Override
	public void delete(Long id) {
		rateRepositoryPort.delete(id);
	}

	@Override
	public Optional<Rate> findApplicable(LocalDate date, Long brandId, Long productId) {
		return rateRepositoryPort.findApplicable(date, brandId, productId).map(this::updateRateCurrency);
	}

	private Rate updateRateCurrency(Rate rate) {
		currencyRepositoryPort.findByCode(rate.getCurrencyCode()).ifPresent(currency -> rate.setCurrency(currency));
		return rate;
	}

}
