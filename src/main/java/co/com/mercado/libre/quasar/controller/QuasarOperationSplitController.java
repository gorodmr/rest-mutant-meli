package co.com.mercado.libre.quasar.controller;

import javax.validation.Valid;

import co.com.mercado.libre.quasar.model.SatelliteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.quasar.service.impl.QuasarOperationSplitServiceImpl;
import co.com.mercado.libre.quasar.model.QuasarOperationResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/topsecret_split")
@AllArgsConstructor
public class QuasarOperationSplitController {

	private final QuasarOperationSplitServiceImpl quasarOperationSplitService;

	@PostMapping(value = "/{satelliteName}")
	public ResponseEntity<QuasarOperationResponse> postTopSecretSplit(
			@PathVariable(name = "satelliteName") String satelliteName, @RequestBody @Valid SatelliteRequest satelliteRequest) {
		satelliteRequest.setName(satelliteName);
		quasarOperationSplitService.addSatelliteSplitInformation(satelliteRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuasarOperationResponse> getTopSecretSplit() {
		QuasarOperationResponse response = quasarOperationSplitService
				.getQuasarOperation(quasarOperationSplitService.getQuasarOperationRequest());
		quasarOperationSplitService.disableSatellitesSplitStatus();
		return ResponseEntity.ok(response);
	}
}
