package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.api.generated.model.ErrorResponse;
import com.academy.finaltask.api.generated.model.TaskRequest;
import com.academy.finaltask.api.generated.model.TaskResponse;
import com.academy.finaltask.api.generated.model.TasksResponse;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.exceptions.NullFieldException;
import com.academy.finaltask.core.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.academy.finaltask.api.generated.*;
import org.springframework.web.context.request.WebRequest;


import java.text.ParseException;
import java.util.List;

@Controller
public class TaskController implements TaskApi{

    @Autowired
    TaskConverter taskConverter;

    @Autowired
    TaskService taskService;

    @Override
    public ResponseEntity<TaskResponse> create(TaskRequest taskRequest) throws EntityExistsException, NullFieldException {
        Task createdTask = taskService.create(taskConverter.taskFromRequest(taskRequest));
        TaskResponse taskResponse = taskConverter.toTaskResponse(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @Override
    public ResponseEntity<TasksResponse> getAll(){
        TasksResponse tasksResponse = taskConverter.toTasksResponse(taskService.findAll());
        return ResponseEntity.status(HttpStatus.FOUND).body(tasksResponse);
    }



}
