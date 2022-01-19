package com.academy.finaltask.core.repositories;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends CrudRepository<Task, Long> {
    boolean existsByTitle(String title);
    List<Task> findAllByAssignee(Employee assignee);

    @Transactional
    void deleteAllByAssignee(Employee assignee);
    @Transactional
    void deleteByTitle(String title);

    boolean existsByTitleAndAssignee(String title, Employee assignee);
}
