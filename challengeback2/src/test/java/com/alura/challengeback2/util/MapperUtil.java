package com.alura.challengeback2.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil<T, Y> {

    private final ModelMapper mapper;
    private final Class<T> entityClass;
    private final Class<Y> dtoClass;

    public MapperUtil(ModelMapper mapper, Class<T> entityClass, Class<Y> dtoClass) {
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public Y mapEntityToDto(T entity) {
        return mapper.map(entity, dtoClass);
    }

    public T mapDtoToEntity(Y dto) {
        return mapper.map(dto, entityClass);
    }

    public List<Y> mapEntityListToDto(List<T> entityList) {
        return entityList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public List<T> mapDtoListToEntity(List<Y> dtoList) {
        return dtoList.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
    }
}
