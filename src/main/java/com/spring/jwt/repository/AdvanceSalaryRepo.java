package com.spring.jwt.repository;
import com.spring.jwt.entity.AdvanceSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvanceSalaryRepo extends JpaRepository<AdvanceSalary, Integer> {

    @Query("SELECT a FROM AdvanceSalary a WHERE a.advanceEmployeId = :employeeId")
    Optional<AdvanceSalary> findByEmployeeId(@Param("employeeId") Integer employeeId);
}