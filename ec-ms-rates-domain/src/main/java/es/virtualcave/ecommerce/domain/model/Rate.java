package es.virtualcave.ecommerce.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {

	private final Long id;

	private final Long branchId;

	private final Long productId;

	private final LocalDate startDate;

	private final LocalDate endDate;
	
	private final BigDecimal price;

	private final Currency currency;

}
