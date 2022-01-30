package com.academy.finaltask.core.services;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeService employeeService;

    public List<Task> findAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public List<Task> findAllByAssignee(Employee assignee){
        return taskRepository.findAllByAssignee(assignee);
    }

    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    public Task create(Task task) throws EntityExistsException {
        if (task.isValidTask()){
            throwIfTaskAlreadyExists(task);
        }
        return save(task);
    }

    public Task update(Task task) throws EntityExistsException {
        if(task.isValidTask()){
            return save(task);
        }
        else {
            return null;
        }
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    public void deleteAll(){
        taskRepository.deleteAll();
    }

    public void deleteAllByAssignee(Employee assignee){
        taskRepository.deleteAllByAssignee(assignee);
    }

    public void deleteByTitle(String title){
        taskRepository.deleteByTitle(title);
    }

    private Task save(Task task) throws EntityExistsException {
        Task savedTask = null;
        try {
            Employee newEmployee = task.getAssignee();
            if (employeeService.existsByFirstAndLastName(newEmployee.getFirstName(), newEmployee.getLastName())){
                task.setAssignee(employeeService.findByFirstAndLastName(newEmployee.getFirstName(), newEmployee.getLastName()));
            }
            else {
                employeeService.create(newEmployee);
            }
            savedTask = taskRepository.save(task);

        } catch (DataIntegrityViolationException e){
            throw new EntityExistsException("Task already exists");
        }
        return savedTask;
    }

    private void throwIfTaskAlreadyExists(Task task) throws EntityExistsException {
        boolean taskExists = taskRepository.existsByTitle(task.getTitle());
        if (!taskExists) {
            return;
        }
        throw new EntityExistsException("Task already exists");
    }

}
