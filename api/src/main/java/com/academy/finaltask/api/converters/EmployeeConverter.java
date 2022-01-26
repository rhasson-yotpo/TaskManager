package com.academy.finaltask.api.converters;

import com.academy.finaltask.core.entities.Employee;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {
    public EmployeeConverter(){}

    public Employee employeeFromRequest(RequestEntity<String> request) throws JSONException {
        return employeeFromJSONObject(new JSONObject(request.getBody()));
    }

    public List<Employee> employeesFromRequest(RequestEntity<String> request) throws JSONException {
        JSONArray objs = new JSONObject(request.getBody()).getJSONArray("employees");
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < objs.length(); i++){
            JSONObject obj = objs.getJSONObject(i);
            employees.add(employeeFromJSONObject(obj));
        }
        return employees;
    }

    public List<String> toEmployeesResponse(List<Employee> employees){
        return employees.stream().map(this::toEmployeeResponse).collect(Collectors.toList());
    }

    public String toEmployeeResponse(Employee employee) {
        try{
            return new JSONObject()
                    .put("Id", employee.getEmployeeId())
                    .put("FirstName", employee.getFirstName())
                    .put("LastName", employee.getLastName())
                    .toString();
        } catch (JSONException e){
            return "Issue parsing employee JSON";
        }
    }

    public JSONObject toEmployeeObjectResponse(Employee employee) throws JSONException {
        return new JSONObject()
                .put("Id", employee.getEmployeeId())
                .put("FirstName", employee.getFirstName())
                .put("LastName", employee.getLastName());
    }

    public Employee employeeFromJSONObject(JSONObject employeeJSON) throws JSONException {
        return new Employee(
                employeeJSON.getString("first"),
                employeeJSON.getString("last")
        );
    }
}
