package com.atipera.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GitHubRepository {

    private final String name;
    private final boolean fork;
    private final GitHubOwner owner;

    public static GitHubRepository of(String name, boolean fork, GitHubOwner owner) {
        requireNonNull(name, "Name cannot be null");
        requireNonNull(owner, "Git hub owner cannot be null");
        return new GitHubRepository(name, fork, owner);
    }
}
