package com.lean.tech.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeDto implements Serializable {
    private Long id;
    private PersonDto person;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PositionDto position;
    private BigDecimal salary;
}
