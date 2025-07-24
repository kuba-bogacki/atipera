package com.atipera.configuration.client;

import com.atipera.exception.GitHubUserNotFoundException;
import com.atipera.model.GitHubBranch;
import com.atipera.model.GitHubOwner;
import com.atipera.model.GitHubRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.atipera.util.ApplicationConstants.*;

@Component
public class DefaultGitHubClient implements GitHubClient {

    private final String gitHubUrl;
    private final RestTemplate restTemplate;

    public DefaultGitHubClient(@Value("${github.api.url:}") String gitHubUrl, RestTemplate restTemplate) {
        this.gitHubUrl = gitHubUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public void validateUserExist(String username) {
        try {
            var url = String.format(USER_URL, gitHubUrl, username);
            restTemplate.getForEntity(url, GitHubOwner.class);
        } catch (HttpClientErrorException exception) {
            throw new GitHubUserNotFoundException(exception.getMessage());
        }
    }

    @Override
    public List<GitHubRepository> fetchUserRepositories(String username) {
        var url = String.format(USER_REPOSITORIES_URL, gitHubUrl, username);
        var repositories = restTemplate.getForEntity(url, GitHubRepository[].class);
        return repositories.getBody() == null ? List.of() : Arrays.asList(repositories.getBody());
    }

    @Override
    public List<GitHubBranch> fetchRepositoryBranches(String username, String repositoryName) {
        var url = String.format(REPOSITORY_BRANCHES_URL, gitHubUrl, username, repositoryName);
        var branches = restTemplate.getForEntity(url, GitHubBranch[].class);
        return branches.getBody() == null ? List.of() : Arrays.asList(branches.getBody());
    }
}
