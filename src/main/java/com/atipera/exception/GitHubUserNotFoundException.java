package com.atipera.exception;

public class GitHubUserNotFoundException extends RuntimeException {

    public GitHubUserNotFoundException(String message) {
        super(message);
    }
}
