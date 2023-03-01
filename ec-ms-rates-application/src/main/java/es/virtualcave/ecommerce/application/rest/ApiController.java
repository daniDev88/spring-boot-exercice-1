package es.virtualcave.ecommerce.application.rest;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import es.virtualcave.application.rest.api.DefaultApi;
import es.virtualcave.application.rest.model.InlineObjectDto;
import es.virtualcave.application.rest.model.RateDto;
import es.virtualcave.application.rest.model.RateInDto;

@RestController
public class ApiController implements DefaultApi{

	@Override
	public ResponseEntity<Void> create(RateInDto rateInDto) {
		return DefaultApi.super.create(rateInDto);
	}

	@Override
	public ResponseEntity<Void> deletById(Integer id) {
		return DefaultApi.super.deletById(id);
	}

	@Override
	public ResponseEntity<RateDto> findByid(Integer id) {
		return DefaultApi.super.findByid(id);
	}

	@Override
	public ResponseEntity<RateDto> getApplicable(LocalDate date, Integer productId, Integer brandId) {
		return DefaultApi.super.getApplicable(date, productId, brandId);
	}

	@Override
	public ResponseEntity<Void> updateRatePrice(Integer id, InlineObjectDto inlineObjectDto) {
		return DefaultApi.super.updateRatePrice(id, inlineObjectDto);
	}

}
