package co.trucom.footballfantokens;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.trucom.footballfantokens.converters.TokenConverter;
import co.trucom.footballfantokens.dtos.FanTokenDto;
import co.trucom.footballfantokens.models.FanToken;
import co.trucom.footballfantokens.services.TokenService;

@SpringBootApplication
public class FootballFanTokensApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FootballFanTokensApplication.class, args);
	}

	@Autowired
	private TokenService tokenService;

	@Override
	public void run(String... args) throws Exception {
		List<FanToken> listTokens = Arrays.asList(
				new FanToken("SCP", "Sporting Clube de Portugal", 22d),
				new FanToken("POR", "Portugal", 11d),
				new FanToken("RMA", "Read Madrid Club de Fútbol", 22d)
		);

		for (FanToken token: listTokens) {
			FanTokenDto dto = TokenConverter.tokenToDto(token);
			System.out.println(dto);
			dto = tokenService.saveToken(dto);
			FanToken savedToken = TokenConverter.dtoToToken(dto);
			System.out.println("Saved Token: " + savedToken);
		}
	}

}
