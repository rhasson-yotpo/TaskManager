package com.academy.finaltask.core.services;


import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean existsByFirstAndLastName(String firstName, String lastName){
        return employeeRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public Employee findByFirstAndLastName(String firstName, String lastName){
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }


    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee create(Employee employee) throws EntityExistsException {
        if (employee.isValidEmployee()){
            throwIfEmployeeAlreadyExists(employee);
        }
        return save(employee);
    }

    public Employee update(Employee employee) throws EntityExistsException {
        if(employee.isValidEmployee()){
            return save(employee);
        }
        else {
            return null;
        }
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }

    public void deleteAll(){
        employeeRepository.deleteAll();
    }

    public void deleteByFirstNameAndLastName(Employee employee){
        employeeRepository.deleteByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
    }

    private Employee save(Employee employee) throws EntityExistsException {
        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e){
            throw new EntityExistsException("Employee already exists");
        }
        return savedEmployee;
    }

    private void throwIfEmployeeAlreadyExists(Employee employee) throws EntityExistsException {
        boolean employeeExists = employeeRepository.existsByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        if (!employeeExists) {
            return;
        }
        throw new EntityExistsException("Employee already exists");
    }
}
