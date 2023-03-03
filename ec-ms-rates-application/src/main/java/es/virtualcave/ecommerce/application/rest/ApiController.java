package es.virtualcave.ecommerce.application.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.virtualcave.application.rest.api.DefaultApi;
import es.virtualcave.application.rest.model.InlineObjectDto;
import es.virtualcave.application.rest.model.RateDto;
import es.virtualcave.application.rest.model.RateInDto;
import es.virtualcave.ecommerce.application.rest.mapper.RateMapper;
import es.virtualcave.ecommerce.domain.api.DomainRateServicePort;
import es.virtualcave.ecommerce.domain.model.Rate;

@RestController
public class ApiController implements DefaultApi {

	private final DomainRateServicePort domainRateServicePort;

	private final RateMapper rateMapper;

	public ApiController(final DomainRateServicePort domainRateServicePort, final RateMapper rateMapper) {
		this.domainRateServicePort = domainRateServicePort;
		this.rateMapper = rateMapper;
	}

	@Override
	public ResponseEntity<Void> create(RateInDto rateInDto) {
		Rate rate = domainRateServicePort.create(rateMapper.asRate(rateInDto)).get();
		return ResponseEntity.created(buildLocationHeader(rate)).build();
	}

	@Override
	public ResponseEntity<Void> deletById(Long id) {
		domainRateServicePort.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<RateDto> findByid(Long id) {
		return ResponseEntity.of(domainRateServicePort.findById(id).map(rateMapper::asRateDto));
	}

	@Override
	public ResponseEntity<RateDto> getApplicable(LocalDate date, Long productId, Long brandId) {
		return ResponseEntity
				.of(domainRateServicePort.findApplicable(date, brandId, productId).map(rateMapper::asRateDto));

	}

	@Override
	public ResponseEntity<Void> updateRatePrice(Long id, InlineObjectDto inlineObjectDto) {
		domainRateServicePort.updatePrice(id, BigDecimal.valueOf(inlineObjectDto.getPrice()));
		return ResponseEntity.ok().build();
	}

	private URI buildLocationHeader(Rate rate) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rate.getId()).toUri();
	}

}
