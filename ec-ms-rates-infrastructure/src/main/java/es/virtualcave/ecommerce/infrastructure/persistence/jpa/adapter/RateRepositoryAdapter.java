package es.virtualcave.ecommerce.infrastructure.persistence.jpa.adapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import es.virtualcave.ecommerce.domain.model.Rate;
import es.virtualcave.ecommerce.domain.spi.RateRepositoryPort;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.entity.RateDbEntity;
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
		Optional<RateDbEntity> rateEntity = Optional
				.ofNullable(rateRepository.save(rateDbEntityMapper.asRateDbEntity(rate)));
		return rateEntity.map(rateDbEntityMapper::asRate);
	}

	@Override
	public Optional<Rate> findById(Long id) {
		return rateRepository.findById(id).map(rateDbEntityMapper::asRate);
	}

	@Override
	public void updatePrice(Long rateId, BigDecimal price) {
		Optional<RateDbEntity> optionalRate = rateRepository.findById(rateId);
		optionalRate.ifPresent(rate -> {
			rate.setPrice(price);
			rateRepository.save(rate);
		});
	}

	@Override
	public void delete(Long rateId) {
		rateRepository.deleteById(rateId);
	}

	@Override
	public Optional<Rate> findApplicable(LocalDate date, Long brandId, Long productId) {
		return rateRepository.findApplicableRate(date, brandId, productId)
				.map(rateDbEntityMapper::asRate);
	}

}
