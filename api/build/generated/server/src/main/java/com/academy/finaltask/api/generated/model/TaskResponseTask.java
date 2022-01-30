package com.academy.finaltask.api.generated.model;

import java.util.Objects;
import com.academy.finaltask.api.generated.model.EmployeeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TaskResponseTask
 */

public class TaskResponseTask   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("status")
  private String status;

  @JsonProperty("assignee")
  private EmployeeResponse assignee;

  @JsonProperty("dueDate")
  private LocalDate dueDate;

  public TaskResponseTask id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TaskResponseTask title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TaskResponseTask status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public TaskResponseTask assignee(EmployeeResponse assignee) {
    this.assignee = assignee;
    return this;
  }

  /**
   * Get assignee
   * @return assignee
  */
  @ApiModelProperty(value = "")

  @Valid

  public EmployeeResponse getAssignee() {
    return assignee;
  }

  public void setAssignee(EmployeeResponse assignee) {
    this.assignee = assignee;
  }

  public TaskResponseTask dueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**
   * Get dueDate
   * @return dueDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskResponseTask taskResponseTask = (TaskResponseTask) o;
    return Objects.equals(this.id, taskResponseTask.id) &&
        Objects.equals(this.title, taskResponseTask.title) &&
        Objects.equals(this.status, taskResponseTask.status) &&
        Objects.equals(this.assignee, taskResponseTask.assignee) &&
        Objects.equals(this.dueDate, taskResponseTask.dueDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, status, assignee, dueDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskResponseTask {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
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

