package com.freelance.mapper;

import com.freelance.dto.UserPartyDto;
import com.freelance.entity.AuthEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        Converter<AuthEntity, UserPartyDto> myConverter = new Converter<AuthEntity, UserPartyDto>() {
//            @Override
//            public UserPartyDto convert(MappingContext<AuthEntity, UserPartyDto> mappingContext) {
//                ModelMapper modelMapper = new ModelMapper();
//
//                return null;
//            }
//        }
        return modelMapper;
    }
}
