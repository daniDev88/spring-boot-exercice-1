package es.virtualcave.ecommerce.infrastructure.persistence.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_RATES")
public final class RateDbEntity implements Serializable{

	private static final long serialVersionUID = -2032334909756935490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BRAND_ID")
	private Long brandId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "START_DATE")
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;
}
