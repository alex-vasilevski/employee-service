package com.mangement.exception;

import lombok.Getter;

@Getter
public class ProjectNotFoundException extends Exception {

    private String message;

    public ProjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
