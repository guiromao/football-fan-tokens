package co.trucom.footballfantokens.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.trucom.footballfantokens.dtos.FanTokenDto;
import co.trucom.footballfantokens.services.TokenService;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@GetMapping({"", "/"})
	public ResponseEntity<List<FanTokenDto>> getAllTokens() {
		return new ResponseEntity<>(tokenService.listTokens(), HttpStatus.OK);
	}

	@GetMapping("/{code}")
	public ResponseEntity<FanTokenDto> getToken(@PathVariable String code) {
		return new ResponseEntity<>(tokenService.getTokenByCode(code), HttpStatus.OK);
	}

	@PostMapping({"", "/"})
	public ResponseEntity<FanTokenDto> saveToken(@RequestBody FanTokenDto dto) {
		return new ResponseEntity<>(tokenService.saveToken(dto), HttpStatus.CREATED);
	}

}
