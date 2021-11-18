package com.lean.tech.mapper;

import com.lean.tech.dto.EmployeeDto;
import com.lean.tech.model.Employee;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface EmployeeMapper {


    EmployeeDto toDto(Employee employee);

    @IterableMapping(qualifiedByName="mapWithoutPosition")
    List<EmployeeDto> toDto(List<Employee> employee);

    @Named("mapWithoutPosition")
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "person.id", ignore = true)
    EmployeeDto mapWithoutPosition(Employee employee);

    Employee toModel(EmployeeDto employee);
}
