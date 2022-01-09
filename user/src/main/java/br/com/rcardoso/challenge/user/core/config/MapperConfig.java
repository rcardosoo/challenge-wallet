package br.com.rcardoso.challenge.user.core.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class MapperConfig {

    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private MapperConfig() {
    }

    public static <S, T> S convert(final T source, Class<S> targetClass) {
        return modelMapper.map(source, targetClass);
    }

}
