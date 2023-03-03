package es.virtualcave.ecommerce.application.rest.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import es.virtualcave.application.rest.model.RateDto;
import es.virtualcave.application.rest.model.RateInDto;
import es.virtualcave.ecommerce.domain.model.Rate;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface RateMapper {

	@Mapping(target = "currencyCode", source = "currency.code")
	@Mapping(target = "currencySymbol", source = "currency.symbol")
	@Mapping(target = "price", expression = "java(rate.getFormattedPrice())")
	RateDto asRateDto(Rate rate);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "currency.code", source = "currencyCode")
	Rate asRate(RateInDto rate);

}
