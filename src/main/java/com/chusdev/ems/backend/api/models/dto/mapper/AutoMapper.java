package com.chusdev.ems.backend.api.models.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper {
    @Autowired
    ModelMapper modelMapper;

    public <T, R> List<R> listaMapper(List<T> listaOrigen, Class<R> tipoDestino) {
        return listaOrigen.stream()
                .map(elemento -> modelMapper.map(elemento, tipoDestino))
                .collect(Collectors.toList());
    }
    
    public <T, R> R mapper(T origen, Class<R> tipoDestino) {
        return modelMapper.map(origen, tipoDestino);
    }
}
