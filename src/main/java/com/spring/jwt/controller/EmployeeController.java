package com.spring.jwt.controller;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.service.EmployeeService;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> createEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new BaseResponseDTO("Successful", "Employee created with ID: " + createdEmployee.getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @GetMapping("/{id}/{referenceId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id,@PathVariable Integer referenceId) {
        try {
            Employee employee = employeeService.getEmployeeById(id,referenceId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponceDto("Successful",employee));

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getAllEmployees(@PathVariable Integer referenceId) {
        try {
            List<Employee> employees = employeeService.getAllEmployees(referenceId);
            return ResponseEntity.ok(new ResponceDto("Successful", employees));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/{referenceId}")
    public ResponseEntity<BaseResponseDTO> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails,@PathVariable Integer referenceId) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails,referenceId);
            return ResponseEntity.ok(new BaseResponseDTO("Successful", "Employee updated with ID: " + updatedEmployee.getId()));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}/{referenceId}")
    public ResponseEntity<BaseResponseDTO> deleteEmployee(@PathVariable Integer id,@PathVariable Integer referenceId) {
        try {
            employeeService.deleteEmployee(id,referenceId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new BaseResponseDTO("Successful", "Employee deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        }
    }
}