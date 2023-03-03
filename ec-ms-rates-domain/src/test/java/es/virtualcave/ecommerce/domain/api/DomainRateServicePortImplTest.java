package es.virtualcave.ecommerce.domain.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.virtualcave.ecommerce.domain.model.Currency;
import es.virtualcave.ecommerce.domain.model.Rate;
import es.virtualcave.ecommerce.domain.spi.CurrencyRepositoryPort;
import es.virtualcave.ecommerce.domain.spi.RateRepositoryPort;

public class DomainRateServicePortImplTest {

	@Mock
	private RateRepositoryPort rateRepositoryPort;

	@Mock
	private CurrencyRepositoryPort currencyRepositoryPort;

	private DomainRateServicePort domainRateServicePort;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		domainRateServicePort = new DomainRateServicePortImpl(rateRepositoryPort, currencyRepositoryPort);
	}

	@Test
	public void shouldCreate() {
		// Given
		Currency expectedCurrency = createDefaultCurrency();
		Rate expectedRate = createDefaultRate(expectedCurrency);
		when(rateRepositoryPort.create(expectedRate)).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode()))
				.thenReturn(Optional.of(expectedCurrency));

		// When
		Optional<Rate> actualRate = domainRateServicePort.create(expectedRate);

		// Then
		verify(rateRepositoryPort, times(1)).create(expectedRate);
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitCurrency(expectedRate, actualRate.get());
	}

	@Test
	public void shouldCreateWithOnlyCurrencyCode() {
		// Given
		Rate expectedRate = createDefaultRate(Currency.builder().code("USD").build());
		when(rateRepositoryPort.create(expectedRate)).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode())).thenReturn(Optional.empty());

		// When
		Optional<Rate> actualRate = domainRateServicePort.create(expectedRate);

		// Then
		verify(rateRepositoryPort, times(1)).create(expectedRate);
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitOnlyCurrencyCode(expectedRate, actualRate.get());

	}

	@Test
	public void shouldFindById() {
		// Given
		Currency expectedCurrency = createDefaultCurrency();
		Rate expectedRate = createDefaultRate(expectedCurrency);
		when(rateRepositoryPort.findById(expectedRate.getId())).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode()))
				.thenReturn(Optional.of(expectedCurrency));

		// When
		Optional<Rate> actualRate = domainRateServicePort.findById(expectedRate.getId());

		// Then
		verify(rateRepositoryPort, times(1)).findById(expectedRate.getId());
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitCurrency(expectedRate, actualRate.get());
	}

	@Test
	public void shouldFindByIdWithOnlyCurrencyCode() {
		// Given
		Rate expectedRate = createDefaultRate(Currency.builder().code("USD").build());
		when(rateRepositoryPort.findById(expectedRate.getId())).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode())).thenReturn(Optional.empty());

		// When
		Optional<Rate> actualRate = domainRateServicePort.findById(expectedRate.getId());

		// Then
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitOnlyCurrencyCode(expectedRate, actualRate.get());
	}

	@Test
	void shouldUpdatePrice() {
		// Given
		Long rateId = 1L;
		BigDecimal newPrice = BigDecimal.valueOf(20.0);

		// When
		domainRateServicePort.updatePrice(rateId, newPrice);

		// Then
		verify(rateRepositoryPort, times(1)).updatePrice(rateId, newPrice);
	}

	@Test
	void shouldDelete() {
		// Given
		Long rateId = 1L;

		// When
		domainRateServicePort.delete(rateId);

		// Then
		verify(rateRepositoryPort, times(1)).delete(rateId);
	}

	@Test
	public void shouldFindApplicable() {
		// Givenss
		LocalDate date = LocalDate.now();
		Long brandId = 1L;
		Long productId = 1L;
		Currency expectedCurrency = createDefaultCurrency();
		Rate expectedRate = Rate.builder().id(1L).brandId(brandId).productId(productId).currency(expectedCurrency)
				.build();
		when(rateRepositoryPort.findApplicable(date, brandId, productId)).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode())).thenReturn(Optional.empty());

		// When
		Optional<Rate> actualRate = domainRateServicePort.findApplicable(date, brandId, productId);

		// Then
		verify(rateRepositoryPort, times(1)).findApplicable(date, brandId, productId);
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitCurrency(expectedRate, actualRate.get());

	}

	@Test
	public void shouldFindApplicableWithOnlyCurrencyCode() {
		// Given
		LocalDate date = LocalDate.now();
		Long brandId = 1L;
		Long productId = 1L;
		Rate expectedRate = Rate.builder().id(1L).brandId(brandId).productId(productId)
				.currency(Currency.builder().code("USD").build()).build();
		when(rateRepositoryPort.findApplicable(date, brandId, productId)).thenReturn(Optional.of(expectedRate));
		when(currencyRepositoryPort.findByCode(expectedRate.getCurrencyCode())).thenReturn(Optional.empty());

		// When
		Optional<Rate> actualRate = domainRateServicePort.findApplicable(date, brandId, productId);

		// Then
		verify(rateRepositoryPort, times(1)).findApplicable(date, brandId, productId);
		verify(currencyRepositoryPort, times(1)).findByCode(expectedRate.getCurrencyCode());
		assertRateWitOnlyCurrencyCode(expectedRate, actualRate.get());
	}

	private void assertRateWitCurrency(Rate expectedRate, Rate actualRate) {
		assertNotNull(actualRate);
		assertEquals(expectedRate, actualRate);
		assertEquals(expectedRate.getCurrencyCode(), actualRate.getCurrencyCode());
		assertEquals(expectedRate.getCurrency().getDecimals(), actualRate.getCurrency().getDecimals());
		assertEquals(expectedRate.getCurrency().getSymbol(), actualRate.getCurrency().getSymbol());
	}

	private void assertRateWitOnlyCurrencyCode(Rate expectedRate, Rate actualRate) {
		assertNotNull(actualRate);
		assertEquals(expectedRate, actualRate);
		assertEquals(expectedRate.getCurrencyCode(), actualRate.getCurrencyCode());
		assertNull(actualRate.getCurrency().getDecimals());
		assertNull(actualRate.getCurrency().getSymbol());
	}

	private Rate createDefaultRate(Currency currency) {
		Rate rate = Rate.builder().id(1L).brandId(2L).productId(1L).price(BigDecimal.valueOf(10.0))
				.endDate(LocalDate.now()).startDate(LocalDate.now()).currency(currency).build();
		return rate;
	}

	private Currency createDefaultCurrency() {
		Currency currency = Currency.builder().code("USD").symbol("$").decimals(2).build();
		return currency;
	}

}
