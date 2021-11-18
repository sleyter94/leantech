package com.lean.tech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PositionDto {

    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EmployeeDto> employees;
}
