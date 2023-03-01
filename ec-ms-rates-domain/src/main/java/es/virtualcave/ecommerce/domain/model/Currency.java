package es.virtualcave.ecommerce.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Currency {

	private final String code;

	private final String symbol;

	private final Integer decimals;
}
