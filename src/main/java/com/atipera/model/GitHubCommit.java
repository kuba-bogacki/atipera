package com.atipera.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitHubCommit {

    private final String sha;

    public static GitHubCommit of(String sha) {
        requireNonNull(sha, "Sha cannot be null");
        return new GitHubCommit(sha);
    }
}
