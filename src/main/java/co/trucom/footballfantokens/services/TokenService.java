package co.trucom.footballfantokens.services;

import java.util.List;

import co.trucom.footballfantokens.dtos.FanTokenDto;

public interface TokenService {

	List<FanTokenDto> listTokens();

	FanTokenDto getTokenByCode(String code);

	FanTokenDto saveToken(FanTokenDto dto);

	void deleteToken(String code);

	FanTokenDto updatePrice(String code, Double price);

	boolean tokenExists(String code);

}
