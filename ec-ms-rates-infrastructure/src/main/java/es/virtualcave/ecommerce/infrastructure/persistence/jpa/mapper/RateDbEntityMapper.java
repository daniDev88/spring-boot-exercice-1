package es.virtualcave.ecommerce.infrastructure.persistence.jpa.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import es.virtualcave.ecommerce.domain.model.Rate;
import es.virtualcave.ecommerce.infrastructure.persistence.jpa.entity.RateDbEntity;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface RateDbEntityMapper {
	
	@Mapping(target = "currencyCode", source = "currency.code")
	RateDbEntity asRateDbEntity(Rate rate);
	
	@Mapping(target = "currency.code", source = "currencyCode")
	Rate asRate(RateDbEntity rateDbEntity);

}
