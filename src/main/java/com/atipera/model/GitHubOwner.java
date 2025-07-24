package com.atipera.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitHubOwner {

    private final String login;

    public static GitHubOwner of(String login) {
        requireNonNull(login, "Login cannot be null");
        return new GitHubOwner(login);
    }
}
