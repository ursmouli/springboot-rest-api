package com.app.restapi.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<E, D> {
    E toEntity(D dto);
    D toDto(E dto);

    default List<D> toDtoList(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> toEntityList(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
