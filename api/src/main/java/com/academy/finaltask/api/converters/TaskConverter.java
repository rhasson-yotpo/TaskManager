package com.academy.finaltask.api.converters;

import com.academy.finaltask.core.entities.Status;
import com.academy.finaltask.core.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

    public Task taskFromRequest(RequestEntity<String> request) throws ParseException, JSONException {
        return taskFromJSONObject(new JSONObject(request.getBody()));
    }

    public Task taskFromEditRequest(RequestEntity<String> request) throws ParseException, JSONException {
        return taskWithIdFromJSONObject(new JSONObject(request.getBody()));
    }

    public List<Task> tasksFromRequest(RequestEntity<String> request) throws ParseException, JSONException {
        JSONArray objs = new JSONArray(request.getBody());
        List<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < objs.length(); i++){
            JSONObject obj = objs.getJSONObject(i);
            tasks.add(taskFromJSONObject(obj));
        }
        return tasks;
    }

    public Task taskFromJSONObject(JSONObject obj) throws ParseException, JSONException {
        return new Task(
                obj.getString("title"),
                employeeConverter.employeeFromJSONObject((JSONObject) obj.get("assignee")),
                new SimpleDateFormat("ddMMyyyy").parse(obj.getString("due")),
                statusFromString(obj.getString("status"))
        );
    }

    public Task taskWithIdFromJSONObject(JSONObject obj) throws ParseException, JSONException{
        return new Task(
                Long.parseLong(obj.getString("Id")),
                obj.getString("title"),
                employeeConverter.employeeFromJSONObject((JSONObject) obj.get("assignee")),
                new SimpleDateFormat("ddMMyyyy").parse(obj.getString("due")),
                statusFromString(obj.getString("status"))
        );
    }

    public String toTaskResponse(Task task) {
        try {
            return new JSONObject()
                    .put("Id", task.getTaskId())
                    .put("Title", task.getTitle())
                    .put("Assignee", employeeConverter.toEmployeeObjectResponse(task.getAssignee()))
                    .put("DueDate", task.getDueDate().toString())
                    .put("Status", task.getStatus().toString())
                    .toString();
        } catch (JSONException e){
            return "Issue parsing task JSON";
        }
    }

    public List<String> toTasksResponse(List<Task> tasks){
        return tasks.stream().map(this::toTaskResponse).collect(Collectors.toList());
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
