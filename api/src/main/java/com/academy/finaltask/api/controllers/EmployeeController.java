package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.EmployeeConverter;
import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.services.EmployeeService;
import com.academy.finaltask.core.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
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
}
