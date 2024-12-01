package com.spring.jwt.service;
import com.spring.jwt.entity.Employee;
import com.spring.jwt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id,Integer referenceId) {
        return employeeRepository.findByIdAndReferenceId(id,referenceId).orElseThrow(()->new RuntimeException("Employee not found by id") );
    }

    public List<Employee> getAllEmployees(Integer referenceId) {
        return employeeRepository.findAllByReferenceId(referenceId);
    }

    public Employee updateEmployee(Integer id, Employee employeeDetails,Integer referenceId) {
        Employee employee = employeeRepository.findByIdAndReferenceId(id,referenceId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employeeDetails.getFirstName() != null) {
            employee.setFirstName(employeeDetails.getFirstName());
        }
        if (employeeDetails.getLastName() != null) {
            employee.setLastName(employeeDetails.getLastName());
        }
        if (employeeDetails.getDateOfBirth() != null) {
            employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        }
        if (employeeDetails.getEmail() != null) {
            employee.setEmail(employeeDetails.getEmail());
        }
        if (employeeDetails.getMobileNo() != null) {
            employee.setMobileNo(employeeDetails.getMobileNo());
        }
        if (employeeDetails.getSalary() != null) {
            employee.setSalary(employeeDetails.getSalary());
        }
        if (employeeDetails.getHireDate() != null) {
            employee.setHireDate(employeeDetails.getHireDate());
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id,Integer referenceId) {
//        employeeRepository.deleteById(id,referenceId);
    }
}