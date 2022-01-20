package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskConverter taskConverter;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<String> create(RequestEntity<String> request) throws ParseException {
        try {
            Task createdTask = taskService.create(taskConverter.taskFromRequest(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(taskConverter.toTaskResponse(createdTask));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task already exists");
        }
        catch (JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect JSON Formatting");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit")
    public ResponseEntity<String> edit (RequestEntity<String> request) throws ParseException {
        try {
            Task editedTask = taskService.update(taskConverter.taskFromEditRequest(request));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskConverter.toTaskResponse(editedTask));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task already exists, no edits made");
        }
        catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect JSON Formatting, Id is necessary for edit");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<String> getAll(RequestEntity<String> request) throws ParseException, EntityExistsException {
        List<Task> tasks = (List<Task>) taskService.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskConverter.toTasksResponse(tasks).toString());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll(RequestEntity<String> request){
        taskService.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
    }





    /*
    TODO:
     Add multiple tasks - not super important right away
     Get all for one employee : filter and sort
     Delete task, Delete all for one employee

     Calls service to check validity and execute action
     returns response entity
     */



}
