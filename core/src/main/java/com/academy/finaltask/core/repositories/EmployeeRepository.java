package com.academy.finaltask.core.repositories;


import com.academy.finaltask.core.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    Employee findByFirstNameAndLastName(String firstName, String lastName);
}
