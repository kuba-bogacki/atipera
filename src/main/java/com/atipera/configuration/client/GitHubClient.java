package com.atipera.configuration.client;

import com.atipera.model.GitHubBranch;
import com.atipera.model.GitHubRepository;

import java.util.List;

public interface GitHubClient {
    void validateUserExist(String username);
    List<GitHubRepository> fetchUserRepositories(String username);
    List<GitHubBranch> fetchRepositoryBranches(String username, String repositoryName);
}
