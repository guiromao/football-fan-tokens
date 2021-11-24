package co.trucom.footballfantokens.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import co.trucom.footballfantokens.dtos.FanTokenDto;
import co.trucom.footballfantokens.models.FanToken;

public class TokenConverter {

	private static ModelMapper modelMapper = new ModelMapper();

	public static FanTokenDto tokenToDto(FanToken token) {
		return modelMapper.map(token, FanTokenDto.class);
	}

	public static FanToken dtoToToken(FanTokenDto dto) {
		return modelMapper.map(dto, FanToken.class);
	}

	public static List<FanTokenDto> listTokenToListDto(List<FanToken> listToken) {
		return listToken.stream()
				.map(token -> tokenToDto(token))
				.collect(Collectors.toList());
	}

	public static List<FanToken> listDtoToListToken(List<FanTokenDto> listDto) {
		return listDto.stream()
				.map(dto -> dtoToToken(dto))
				.collect(Collectors.toList());
	}

}
