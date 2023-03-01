package es.virtualcave.ecommerce.domain.spi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Rate;

public interface RateRepositoryPort {

	Optional<Rate> create(Rate rate);

	Optional<Rate> findById(Long id);

	void updatePrice(Long rateId, BigDecimal price);

	void delete(Long rateId);

	Optional<Rate> findApplicable(LocalDate date, Long brandId, Long productId);

}
