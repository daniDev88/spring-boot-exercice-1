package es.virtualcave.ecommerce.infrastructure.currency.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import es.virtualcave.ecommerce.domain.model.Currency;
import es.virtualcave.infrastructure.currency.rest.model.CurrencyDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface CurrencyMapper {

	Currency asCurrency(CurrencyDto currency);

}
