package es.virtualcave.ecommerce.infrastructure.persistence.jpa.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Rate;
import es.virtualcave.ecommerce.domain.spi.RateRepositoryPort;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.mapper.RateDbEntityMapper;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.repository.RateRepository;

public class RateRepositoryAdapter implements RateRepositoryPort {
	
	private final RateRepository rateRepository;
	
	private final RateDbEntityMapper rateDbEntityMapper;

	public RateRepositoryAdapter(final RateRepository rateRepository, final RateDbEntityMapper rateDbEntityMapper) {
		this.rateRepository = rateRepository;
		this.rateDbEntityMapper = rateDbEntityMapper;
	}

	@Override
	public Optional<Rate> create(Rate rate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Rate> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePrice(Long rateId, BigDecimal price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long rateId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Rate> findApplicable(LocalDate date, Long brandId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
