package com.lean.tech.repository;

import com.lean.tech.model.Employee;
import com.lean.tech.model.Person;
import com.lean.tech.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void listByPosition(){
        Position position = Position.builder()
                .id(1)
                .name("dev")
                .build();
        position = positionRepository.save(position);
        Person person = Person.builder()
                .address("Lima")
                .cellphone("123456789")
                .cityName("VES")
                .name("Sleyter")
                .lastName("Sandoval")
                .build();
        person = personRepository.save(person);
        Employee employee = Employee.builder()
                .id(1L)
                .salary(BigDecimal.TEN)
                .position(position)
                .person(person)
                .build();
        employeeRepository.save(employee);

        var employees = employeeRepository.findByPosition("dev");
        assertEquals(employees.size(),1);
        assertEquals(employees.get(0).getSalary(), employee.getSalary());
        assertEquals(employees.get(0).getPerson().getName(), person.getName());
        assertEquals(employees.get(0).getPosition().getName(), position.getName());
    }

    @Test
    public void listByPositionOrName(){
        Position position = Position.builder()
                .id(1)
                .name("dev")
                .build();
        position = positionRepository.save(position);
        Person person = Person.builder()
                .address("Lima")
                .cellphone("123456789")
                .cityName("VES")
                .name("Sleyter")
                .lastName("Sandoval")
                .build();
        person = personRepository.save(person);
        Employee employee = Employee.builder()
                .id(1L)
                .salary(BigDecimal.TEN)
                .position(position)
                .person(person)
                .build();
        employeeRepository.save(employee);

        var employees = employeeRepository.findByPositionOrName("Sleyter","");
        var employees2 = employeeRepository.findByPositionOrName("","qa");
        assertEquals(employees.size(),1);
        assertEquals(employees.get(0).getSalary(), employee.getSalary());
        assertEquals(employees.get(0).getPerson().getName(), person.getName());
        assertEquals(employees.get(0).getPosition().getName(), position.getName());
        assertEquals(employees2.size(),0);
    }
}
