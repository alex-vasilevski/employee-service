package com.bank.rest;

import com.bank.domain.Employee;
import com.bank.domain.Role;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.service.internal.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController{

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeServiceImpl service;

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        service.create(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@NotNull @PathVariable Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findAllMatchingAndSort(@RequestParam(name = "name", required = false) String name,
                                                                 @RequestParam(name = "last_name", required = false) String lastName,
                                                                 @RequestParam(name = "birth_day", required = false) LocalDate birthDay,
                                                                 @RequestParam(name = "age", required = false) Integer age,
                                                                 @RequestParam(name = "salary", required = false) Double salary,
                                                                 @RequestParam (name = "role", required = false) Role role,
                                                                 @RequestParam(name = "direction", required = false) String direction,
                                                                 @RequestParam(name = "sort_param", required = false) Set<String> sortParams) throws EmployeeNotFoundException {

        return ResponseEntity.ok(service.findAllMatchingAndSort(new Employee(name, lastName, birthDay, age, salary, role), direction, sortParams));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@NotNull @PathVariable Long id, @Valid @RequestBody Employee source) throws EmployeeNotFoundException {
        return ResponseEntity.ok(service.update(id, source));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@NotNull @PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}