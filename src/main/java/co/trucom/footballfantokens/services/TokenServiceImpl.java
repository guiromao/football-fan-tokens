package co.trucom.footballfantokens.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.trucom.footballfantokens.converters.TokenConverter;
import co.trucom.footballfantokens.dtos.FanTokenDto;
import co.trucom.footballfantokens.exceptions.TokenException;
import co.trucom.footballfantokens.models.FanToken;
import co.trucom.footballfantokens.repositories.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {
	
	private TokenRepository tokenRepository;
	
	public TokenServiceImpl(TokenRepository tokenRepo) {
		this.tokenRepository = tokenRepo;
	}

	@Override
	public List<FanTokenDto> listTokens() {
		List<FanToken> listToken = tokenRepository.findAll();
		List<FanTokenDto> listDto = TokenConverter.listTokenToListDto(listToken);

		return listDto;
	}

	@Override
	public FanTokenDto getTokenByCode(String code) {
		assertCodeIsValid(code);

		return fetchTokenDtoOrNull(code);
	}

	@Override
	public FanTokenDto saveToken(FanTokenDto dto) {
		FanToken token = TokenConverter.dtoToToken(dto);
		token = tokenRepository.saveAndFlush(token);

		return TokenConverter.tokenToDto(token);
	}

	@Override
	public void deleteToken(String code) {
		assertCodeIsValid(code);

		tokenRepository.deleteById(code);
	}

	private FanTokenDto fetchTokenDtoOrNull(String code) {
		FanToken token = tokenRepository.findById(code).orElse(null);
		FanTokenDto dto = null;

		if (token != null) {
			dto = TokenConverter.tokenToDto(token);
		}

		return dto;
	}

	private void assertCodeIsValid(String code) {
		if (code == null || code.isEmpty()) {
			throw new TokenException("Invalid token code provided.");
		}
	}

}
