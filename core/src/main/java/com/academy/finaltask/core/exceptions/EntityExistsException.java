package com.academy.finaltask.core.exceptions;

public class EntityExistsException extends Exception{
    public EntityExistsException(String task_already_exists) {
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
