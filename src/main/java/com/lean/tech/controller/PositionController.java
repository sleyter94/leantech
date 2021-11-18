package com.lean.tech.controller;

import com.lean.tech.dto.PositionDto;
import com.lean.tech.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("positions")
public class PositionController {

    private EmployeeService employeeService;

    public PositionController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> list(){
        return ResponseEntity.ok(employeeService.groupByPosition());
    }
}
