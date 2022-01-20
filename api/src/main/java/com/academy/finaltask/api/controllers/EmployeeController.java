package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.EmployeeConverter;
import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.services.EmployeeService;
import com.academy.finaltask.core.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private TaskConverter taskConverter;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private TaskService taskService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<String> create(RequestEntity<String> request) throws ParseException, EntityExistsException {
        try {
            Employee createdEmployee = employeeService.create(employeeConverter.employeeFromRequest(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeConverter.toEmployeeResponse(createdEmployee));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already exists");
        } catch (JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue with JSON formatting");
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<String> getAll(RequestEntity<String> request) throws ParseException, EntityExistsException {
        List<Employee> employees = (List<Employee>) employeeService.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeConverter.toEmployeesResponse(employees).toString());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll(RequestEntity<String> request){
        employeeService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> getAllForEmployee(RequestEntity<String> request, @PathVariable String firstName, @PathVariable String lastName){
        List<Task> employeeTasks = employeeService.findByFirstAndLastName(firstName, lastName).getTasks();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskConverter.toTasksResponse(employeeTasks).toString());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<String> deleteAllForEmployee(RequestEntity<String> request, @PathVariable String firstName, @PathVariable String lastName){
        List<Task> employeeTasks = employeeService.findByFirstAndLastName(firstName, lastName).getTasks();
        employeeTasks.stream().forEach(task -> taskService.deleteById(task.getTaskId()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("Deleted all tasks for %s %s", firstName, lastName));
    }
}
