package co.trucom.footballfantokens.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.trucom.footballfantokens.converters.TokenConverter;
import co.trucom.footballfantokens.dtos.FanTokenDto;
import co.trucom.footballfantokens.models.FanToken;
import co.trucom.footballfantokens.repositories.TokenRepository;

@ExtendWith(SpringExtension.class)
public class TokenServiceTest {

	TokenService tokenService;

	@MockBean
	TokenRepository tokenRepository;

	FanToken token1;
	FanToken token2;

	@BeforeEach
	void setup() {
		tokenService = new TokenServiceImpl(tokenRepository);
		token1 = new FanToken("SCP", "Sporting Clube de Portugal", 200d);
	}

	@Test
	void saveToken_ShouldReturnRespectiveDto() {
		FanTokenDto dto1 = TokenConverter.tokenToDto(token1);

		Mockito.when(tokenRepository.saveAndFlush(token1)).thenReturn(token1);

		FanTokenDto expectedResultDto = new FanTokenDto("SCP", "Sporting Clube de Portugal", 200d);
		FanToken expectedResulToken = TokenConverter.dtoToToken(expectedResultDto);

		Assertions.assertEquals(expectedResulToken, TokenConverter.dtoToToken(tokenService.saveToken(dto1)));
	}

	@Test
	void getTokenByCode_ShouldReturnRightFanToken() {

		Mockito.when(tokenRepository.findById(token1.getId())).thenReturn(Optional.of(token1));
		FanTokenDto expectedResult = TokenConverter.tokenToDto(token1);

		Assertions.assertTrue(compareDtos(expectedResult, tokenService.getTokenByCode(token1.getId())));
	}

	@Test
	void updatePrice_ShouldUpdatePriceOfToken() {

		Mockito.when(tokenRepository.findById(token1.getId())).thenReturn(Optional.of(token1));
		Mockito.when(tokenRepository.saveAndFlush(token1)).thenReturn(new FanToken(token1.getId(), token1.getClub(), 300d));
		FanTokenDto expectedResult = new FanTokenDto(token1.getId(), token1.getClub(), 300d);
		FanTokenDto result = tokenService.updatePrice(token1.getId(), 300d);

		Assertions.assertTrue(compareDtos(expectedResult, result));
	}

	@Test
	void listAll_ShouldReturnAllFanTokens() {
		token2 = new FanToken("POR", "Portugal", 99d);
		List<FanToken> allTokens = List.of(token1, token2);

		Mockito.when(tokenRepository.findAll()).thenReturn(allTokens);
		List<FanToken> returnedTokens = TokenConverter.listDtoToListToken(tokenService.listTokens());
		boolean testPassed = true;

		for (FanToken token: returnedTokens) {
			if (!allTokens.contains(token)) {
				testPassed = false;
			}
		}

		Assertions.assertTrue(testPassed);
	}

	@Test
	void methodTokenExistsShouldReturnTrueIfTokenExists() {
		Mockito.when(tokenRepository.findById(token1.getId())).thenReturn(Optional.of(token1));

		Assertions.assertTrue(tokenService.tokenExists(token1.getId()));
	}

	@Test
	void methodTokenExistsShouldReturnFalseIfTokenDoesNotExist() {
		Mockito.when(tokenRepository.findById("DOR")).thenReturn(Optional.empty());

		Assertions.assertFalse(tokenService.tokenExists("DOR"));
	}

	private boolean compareDtos(FanTokenDto dto1, FanTokenDto dto2) {
		return dto1.getId().equals(dto2.getId()) &&
				dto1.getClub().equals(dto2.getClub()) &&
				dto1.getPrice().equals(dto2.getPrice());
	}
}
