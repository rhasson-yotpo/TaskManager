package com.academy.finaltask.api.controllers;
import com.academy.finaltask.api.generated.model.ErrorResponse;
import com.academy.finaltask.core.exceptions.EntityExistsException;
import com.academy.finaltask.core.exceptions.NullFieldException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllersExceptionsHandler extends ResponseEntityExceptionHandler {
    private final String EntityExistsString = "Task with same title exists.";
    private final String NullFieldString = "Request contains a null field. All fields are required.";
    private final String EmptyResultString = "Unable to find task with the specified Id";

    @ExceptionHandler(value = {EntityExistsException.class})
    protected ResponseEntity<ErrorResponse> handleExistingEntity(EntityExistsException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.toString());
        errorResponse.setMessage(EntityExistsString);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullFieldException.class})
    protected ResponseEntity<ErrorResponse> handleNullField(NullFieldException e, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.toString());
        errorResponse.setMessage(NullFieldString);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    protected ResponseEntity<ErrorResponse> handleDataAccess(EmptyResultDataAccessException e, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(e.toString());
        errorResponse.setMessage(EmptyResultString);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
