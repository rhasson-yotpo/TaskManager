package com.academy.finaltask.api.generated.model;

import java.util.Objects;
import com.academy.finaltask.api.generated.model.EmployeeResponseEmployee;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EmployeeResponse
 */

public class EmployeeResponse   {
  @JsonProperty("employee")
  private EmployeeResponseEmployee employee;

  public EmployeeResponse employee(EmployeeResponseEmployee employee) {
    this.employee = employee;
    return this;
  }

  /**
   * Get employee
   * @return employee
  */
  @ApiModelProperty(value = "")

  @Valid

  public EmployeeResponseEmployee getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeResponseEmployee employee) {
    this.employee = employee;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeResponse employeeResponse = (EmployeeResponse) o;
    return Objects.equals(this.employee, employeeResponse.employee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employee);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeResponse {\n");
    
    sb.append("    employee: ").append(toIndentedString(employee)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

