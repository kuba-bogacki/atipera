package com.atipera.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitHubBranch {

    private final String name;
    private final GitHubCommit commit;

    public static GitHubBranch of(String name, GitHubCommit commit) {
        requireNonNull(name, "Name cannot be null");
        requireNonNull(commit, "Commit cannot be null");
        return new GitHubBranch(name, commit);
    }
}
