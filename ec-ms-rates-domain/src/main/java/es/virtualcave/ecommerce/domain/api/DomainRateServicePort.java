package es.virtualcave.ecommerce.domain.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import es.virtualcave.ecommerce.domain.model.Rate;

public interface DomainRateServicePort {
	
	Rate create(Rate rate);

	Rate findById(Long id);

	void updatePrice(Long rateId, BigDecimal price);

	void delete(Long rateId);

	Rate findApplicable(LocalDate date, Long brandId, Long productId);

}
