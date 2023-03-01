package es.virtualcave.ecommerce.infrastructure.persistence.jpa.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.virtualcave.ecommerce.infrastructure.persistence.jpa.entity.RateDbEntity;

public interface RateRepository extends CrudRepository<RateDbEntity, Long>{
	
	Optional<RateDbEntity> findByIdAndPrice(Long id, BigDecimal price);
	
}
