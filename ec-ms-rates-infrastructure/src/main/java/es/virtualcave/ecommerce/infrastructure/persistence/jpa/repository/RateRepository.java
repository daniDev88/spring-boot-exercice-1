package es.virtualcave.ecommerce.infrastructure.persistence.jpa.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.virtualcave.ecommerce.infrastructure.persistence.jpa.entity.RateDbEntity;

public interface RateRepository extends CrudRepository<RateDbEntity, Long> {

	@Query("SELECT r FROM RateDbEntity r WHERE (:date BETWEEN r.startDate AND r.endDate) AND r.brandId = :brandId AND r.productId = :productId")
	Optional<RateDbEntity> findApplicableRate(@Param("date") LocalDate date, @Param("brandId") Long brandId,
			@Param("productId") Long productId);
}
