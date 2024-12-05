package com.spring.jwt.controller;


import com.spring.jwt.dto.RegisterDto;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.exception.BaseException;
import com.spring.jwt.exception.UserAlreadyExistException;
import com.spring.jwt.service.EmployeeSalaryService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ESCC")
@RequiredArgsConstructor
public class EmployeeSalaryCalulateController {

    @Autowired
    private EmployeeSalaryService employeeSalaryService;
    @GetMapping("/salaryDetails")
    public ResponseEntity<?> getSalaryBreakDown(@RequestParam Integer EmployeeId ,@RequestParam Integer referenceId ,@RequestParam Integer month){

        try {
            Object response= employeeSalaryService.getSalaryBreakDown(EmployeeId,referenceId,month);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful",response));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful",e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful","Invalid role"));
        }
    }
    @GetMapping("/getSalaryPaidOrUnPaid")
    public ResponseEntity<?> getSalaryPaidOrUnPaid(@RequestParam Integer EmployeeId ,@RequestParam Integer referenceId ,@RequestParam Integer month) {

        try {
            Object response = employeeSalaryService.gateSalaryPaidOrUnpaid(EmployeeId, referenceId, month);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        }
    }
    @PostMapping("/saveSalaryPaidOrUnPaid")
    public ResponseEntity<?> saveSalaryPaidOrUnPaid(@RequestParam Integer EmployeeId ,@RequestParam Integer referenceId ,@RequestParam Integer month) {

        try {
            Object response = employeeSalaryService.updatedSalaryPaidOrUnpaid(EmployeeId, referenceId, month);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        }
    }

    @GetMapping("/advanceAVB")
    public ResponseEntity<?> advanceAVB(@RequestParam Integer EmployeeId) {

        try {
            Object response = employeeSalaryService.advanceAVB(EmployeeId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        }
    }
    @GetMapping("/addAdvanceAVB")
    public ResponseEntity<?> addAdvanceAVB(@RequestParam Integer employeeId ,@RequestParam Integer amount ) {

        try {
            Object response = employeeSalaryService.addAdvanceAVB(employeeId,amount);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        }
    }

    @GetMapping("/deleteAdvanceAVB")
    public ResponseEntity<?> deleteAdvanceAVB(@RequestParam Integer EmployeeId ,@RequestParam Integer amount ) {

        try {
            Object response = employeeSalaryService.deleteAdvanceAVB(EmployeeId,amount);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("Successful", response));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseDTO("Unsuccessful", "Invalid role"));
        }
    }

}
