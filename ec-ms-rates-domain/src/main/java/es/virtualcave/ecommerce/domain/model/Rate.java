package es.virtualcave.ecommerce.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {

	private final Long id;

	private final Long brandId;

	private final Long productId;

	private final LocalDate startDate;

	private final LocalDate endDate;

	private BigDecimal price;

	private Currency currency;

	public String getCurrencyCode() {
		return Optional.ofNullable(currency).map(Currency::getCode).orElse(null);
	}

	public BigDecimal getFormattedPrice() {
		return Optional.ofNullable(currency)
				.flatMap(cur -> Optional.ofNullable(price).filter(pr -> cur.getDecimals() != null)
						.map(pr -> pr.setScale(cur.getDecimals(), RoundingMode.HALF_UP)))
				.orElse(price);
	}

}
