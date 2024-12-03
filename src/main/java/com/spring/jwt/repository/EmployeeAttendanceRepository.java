package com.spring.jwt.repository;

import com.spring.jwt.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {
//    List<EmployeeAttendance> findByEmployeeId(Integer employeeId,Integer referenceId);
//
//    Optional<EmployeeAttendance> findByEmployeeIdAndDate(Integer employeeId, LocalDate date,Integer referenceId);
//
//    void deleteByEmployeeIdAndDate(Integer employeeId, LocalDate date,Integer referenceId);
//
//    List<EmployeeAttendance> findByERMId(Integer employeeId, Integer referenceId, Integer month);
    @Modifying
    @Query("DELETE FROM EmployeeAttendance ea WHERE ea.employeeId = :employeeId AND ea.date = :date AND ea.referenceId = :referenceId")
    void deleteByEmployeeIdAndDate(@Param("employeeId") Integer employeeId, @Param("date") LocalDate date, @Param("referenceId") Integer referenceId);

    @Query("SELECT ea FROM EmployeeAttendance ea WHERE ea.employeeId = :employeeId AND ea.referenceId = :referenceId AND MONTH(ea.date) = :month")
    List<EmployeeAttendance> findByERMId(@Param("employeeId") Integer employeeId, @Param("referenceId") Integer referenceId, @Param("month") Integer month);


    @Query("SELECT ea FROM EmployeeAttendance ea WHERE ea.employeeId = :employeeId AND ea.referenceId = :referenceId")
    List<EmployeeAttendance> findByEmployeeId(@Param("employeeId") Integer employeeId, @Param("referenceId") Integer referenceId);

    @Query("SELECT ea FROM EmployeeAttendance ea WHERE ea.employeeId = :employeeId AND ea.date = :date AND ea.referenceId = :referenceId")
    Optional<EmployeeAttendance> findByEmployeeIdAndDate(@Param("employeeId") Integer employeeId, @Param("date") LocalDate date, @Param("referenceId") Integer referenceId);
}
