package com.lean.tech.repository;

import com.lean.tech.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT emp FROM Employee emp " +
            "JOIN FETCH emp.person per " +
            "JOIN emp.position pos " +
            "WHERE (pos.name = ?1) " +
            "ORDER BY emp.salary DESC")
    List<Employee> findByPosition(String position);

    @Query("SELECT emp FROM Employee emp " +
            "JOIN FETCH emp.person per " +
            "JOIN FETCH emp.position pos " +
            "WHERE (per.name = ?1)")
    List<Employee> findByName(String name);

    @Query("SELECT emp FROM Employee emp " +
            "JOIN FETCH emp.person per " +
            "JOIN FETCH emp.position pos " +
            "WHERE (per.name = ?1 or LENGTH(?1) = 0) and (pos.name = ?2 OR LENGTH(?2) = 0)")
    List<Employee> findByPositionOrName(String name, String position);

}
