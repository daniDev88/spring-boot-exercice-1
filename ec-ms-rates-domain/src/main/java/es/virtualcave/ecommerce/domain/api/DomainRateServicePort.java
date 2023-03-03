package es.virtualcave.ecommerce.domain.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Rate;

public interface DomainRateServicePort {

	Optional<Rate> create(Rate rate);

	Optional<Rate> findById(Long id);

	void updatePrice(Long id, BigDecimal price);

	void delete(Long id);

	Optional<Rate> findApplicable(LocalDate date, Long brandId, Long productId);

}
