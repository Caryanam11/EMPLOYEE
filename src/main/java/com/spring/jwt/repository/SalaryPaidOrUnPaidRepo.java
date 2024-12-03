package com.spring.jwt.repository;

import com.spring.jwt.entity.SalaryPaidOrUnPaid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryPaidOrUnPaidRepo extends JpaRepository<SalaryPaidOrUnPaid,Integer> {
    @Query("SELECT sp FROM SalaryPaidOrUnPaid sp WHERE sp.month = :month AND sp.employeeId = :employeeId AND sp.referenceId = :referenceId")
    Optional<SalaryPaidOrUnPaid> findByMonthAndSalaryPaidOrUnPaidAndReferenceId(
            @Param("month") Integer month,
            @Param("employeeId") Integer employeeId,
            @Param("referenceId") Integer referenceId
    );
}
