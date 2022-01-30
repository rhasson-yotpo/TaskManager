/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.2.2).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.academy.finaltask.api.generated;

import com.academy.finaltask.api.generated.model.TaskRequest;
import com.academy.finaltask.api.generated.model.TasksResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "Default", description = "the Default API")
public interface DefaultApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "post a task", nickname = "create", notes = "", authorizations = {
        @Authorization(value = "bearer")
    }, tags={  })
    @ApiResponses(value = {  })
    @RequestMapping(value = "/task/add",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> create(@ApiParam(value = "" ,required=true )  @Valid @RequestBody TaskRequest taskRequest) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "get all tasks", nickname = "getAll", notes = "", response = TasksResponse.class, authorizations = {
        @Authorization(value = "bearer")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "all current tasks", response = TasksResponse.class),
        @ApiResponse(code = 204, message = "no tasks yet") })
    @RequestMapping(value = "/tasks/all",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<TasksResponse> getAll() throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"tasks\" : [ { \"task\" : { \"dueDate\" : \"2000-01-23\", \"id\" : 0, \"assignee\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\" }, \"title\" : \"title\", \"status\" : \"status\" } }, { \"task\" : { \"dueDate\" : \"2000-01-23\", \"id\" : 0, \"assignee\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\" }, \"title\" : \"title\", \"status\" : \"status\" } } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}