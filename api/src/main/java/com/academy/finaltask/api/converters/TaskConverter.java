package com.academy.finaltask.api.converters;

import com.academy.finaltask.api.generated.model.*;
import com.academy.finaltask.core.entities.Status;
import com.academy.finaltask.core.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskConverter {
    @Autowired
    private EmployeeConverter employeeConverter;

    public TaskConverter() {
    }

    public Task taskFromRequest(TaskRequest taskRequest) throws JSONException {
        TaskRequestTask task = taskRequest.getTask();
        return Task.builder()
                .title(task.getTitle())
                .status(statusFromString(task.getStatus()))
                .dueDate(task.getDueDate())
                .assignee(employeeConverter.employeeFromRequest(task.getAssignee())).build();
    }

    public TaskResponse toTaskResponse(Task task) {
        TaskResponseTask taskResponseTask = new TaskResponseTask();
        taskResponseTask.setId(task.getTaskId());
        taskResponseTask.setTitle(task.getTitle());
        taskResponseTask.setStatus(task.getStatus().toString());
        taskResponseTask.setAssignee(employeeConverter.toEmployeeResponse(task.getAssignee()));
        taskResponseTask.setDueDate(task.getDueDate());
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTask(taskResponseTask);
        return taskResponse;
    }

    public TasksResponse toTasksResponse(List<Task> tasks){
        List<TaskResponse> taskResponses = tasks.stream().map(this::toTaskResponse).collect(Collectors.toList());
        TasksResponse tasksResponse = new TasksResponse();
        tasksResponse.setTasks(taskResponses);
        return tasksResponse;
    }

    public Status statusFromString(String statusString){
        if (statusString.contains("InProgress")){
            return Status.InProgress;
        }
        if (statusString.contains("Finished")){
            return Status.Finished;
        }
        return Status.NotStarted;
    }
}
