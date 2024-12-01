package com.spring.jwt.repository;


import com.spring.jwt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByIdAndReferenceId(Integer id, Integer referenceId);
//
//    void deleteById(Integer id, Integer referenceId);

    List<Employee> findAllByReferenceId(Integer referenceId);
}