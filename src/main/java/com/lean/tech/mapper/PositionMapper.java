package com.lean.tech.mapper;

import com.lean.tech.dto.PositionDto;
import com.lean.tech.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PositionMapper {

    @Mapping(target = "employees", ignore = true)
    PositionDto toDto(Position position);
    Position toModel(PositionDto position);
}
