package com.academy.finaltask.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@SuperBuilder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "employee_id")
    private Employee assignee;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dueDate;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Task() {
    }

    public Task(String title, Employee assignee, LocalDate dueDate, Status status) {
        this.title = title;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task(Long taskId, String title, Employee assignee, LocalDate dueDate, Status status) {
        this.taskId = taskId;
        this.title = title;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isValidTask(){
        return this.getTitle() != null && this.getAssignee() != null && this.getStatus() != null && this.getDueDate() != null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", assignee=" + assignee +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
