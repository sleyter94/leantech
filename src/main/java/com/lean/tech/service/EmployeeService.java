package com.lean.tech.service;

import com.lean.tech.dto.EmployeeDto;
import com.lean.tech.dto.PositionDto;
import com.lean.tech.mapper.EmployeeMapper;
import com.lean.tech.mapper.PersonMapper;
import com.lean.tech.mapper.PositionMapper;
import com.lean.tech.model.Employee;
import com.lean.tech.model.Person;
import com.lean.tech.model.Position;
import com.lean.tech.repository.EmployeeRepository;
import com.lean.tech.repository.PersonRepository;
import com.lean.tech.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private PersonRepository personRepository;
    private EmployeeMapper employeeMapper;
    private PersonMapper personMapper;
    private PositionMapper positionMapper;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository, PersonRepository personRepository, EmployeeMapper employeeMapper, PersonMapper personMapper, PositionMapper positionMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.personRepository = personRepository;
        this.employeeMapper = employeeMapper;
        this.personMapper = personMapper;
        this.positionMapper = positionMapper;
    }

    public EmployeeDto create(EmployeeDto employeeDto) throws Exception {
        Employee employee = employeeMapper.toModel(employeeDto);
        getReferences(employee);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    private void getReferences(Employee employee) throws Exception {
        Person person = Optional.ofNullable(employee.getPerson().getId())
                .flatMap(id -> personRepository.findById(id))
                .orElse(personRepository.save(employee.getPerson()));
        Position position = positionRepository.findById(employee.getPosition().getId())
                .orElseThrow(() -> new Exception("Position doesn't exits"));
        employee.setPerson(person);
        employee.setPosition(position);
    }

    public EmployeeDto update(EmployeeDto employeeDto) throws Exception {
        Employee employee =employeeMapper.toModel(employeeDto);
        getReferences(employee);
        Person person = personMapper.toModel(employeeDto.getPerson());
        employee.getPerson().setAddress(person.getAddress());
        employee.getPerson().setCellphone(person.getCellphone());
        employee.getPerson().setCityName(person.getCityName());
        employee.getPerson().setName(person.getName());
        employee.getPerson().setLastName(person.getLastName());
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void delete(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.getById(employeeDto.getId());
        Person person = employee.getPerson();
        employeeRepository.deleteById(employeeDto.getId());
        personRepository.deleteById(person.getId());
    }

    public List<EmployeeDto> list(String position, String name) {
        position = Optional.ofNullable(position).orElse("");
        name = Optional.ofNullable(name).orElse("");
        List<Employee> employees = employeeRepository.findByPositionOrName(name, position);
        return employees.stream().map(employee -> employeeMapper.toDto(employee)).collect(Collectors.toList());
    }

    public List<PositionDto> groupByPosition() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream()
            .map(position -> {
                var positionDto = positionMapper.toDto(position);
                var employees = employeeRepository.findByPosition(position.getName());
                positionDto.setEmployees(employeeMapper.toDto(employees));
                return positionDto;
            }
            ).collect(Collectors.toList());
    }
}
