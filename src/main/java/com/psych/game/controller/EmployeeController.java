package com.psych.game.controller;

import com.psych.game.model.Employee;
import com.psych.game.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new ResolutionException("Employee id: " + id + "not found"));
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Employee employee){
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new ResolutionException("Employee id: " + id + "not found"));
        e.setName(employee.getName());
        e.setEmail(employee.getEmail());
        return  employeeRepository.save(e);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long id){
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new ResolutionException("Employee id: " + id + "not found"));
        employeeRepository.delete(e);
        return ResponseEntity.ok().build();
    }
}
