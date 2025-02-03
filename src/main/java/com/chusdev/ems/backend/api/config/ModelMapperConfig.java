package com.chusdev.ems.backend.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        //Ajuste del mapeo de Asignatura a AsignaturaDTO
        // modelMapper.addMappings(new PropertyMap<Asignatura, AsignaturaDTO>() {
        //     @Override
        //     protected void configure() {
        //         map().setEstudio_id(source.getEstudio_id());
        //     }
        // });

        return modelMapper;
    }
}
