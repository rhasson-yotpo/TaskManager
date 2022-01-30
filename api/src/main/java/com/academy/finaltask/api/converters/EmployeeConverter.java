package com.academy.finaltask.api.converters;

import com.academy.finaltask.api.generated.model.EmployeeRequest;
import com.academy.finaltask.api.generated.model.EmployeeRequestEmployee;
import com.academy.finaltask.api.generated.model.EmployeeResponse;
import com.academy.finaltask.api.generated.model.EmployeeResponseEmployee;
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

    public Employee employeeFromRequest(EmployeeRequest employeeRequest) throws JSONException {
        EmployeeRequestEmployee employee = employeeRequest.getEmployee();
        return Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName()).build();
    }
/*
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
*/
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponseEmployee employeeResponseEmployee = new EmployeeResponseEmployee();
        employeeResponseEmployee.setId(employee.getEmployeeId());
        employeeResponseEmployee.setFirstName(employee.getFirstName());
        employeeResponseEmployee.setLastName(employee.getLastName());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployee(employeeResponseEmployee);
        return employeeResponse;
    }
/*
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

 */
}
