package com.lean.tech.controller;

import com.lean.tech.dto.EmployeeDto;
import com.lean.tech.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(employeeDto));
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) throws Exception {
        return ResponseEntity.ok(employeeService.update(employeeDto));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> list(@RequestParam(required = false) String position,
                                                  @RequestParam(required = false) String name){
        return ResponseEntity.ok(employeeService.list(position, name));
    }

    @DeleteMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody EmployeeDto employeeDto){
        employeeService.delete(employeeDto);
    }


}
