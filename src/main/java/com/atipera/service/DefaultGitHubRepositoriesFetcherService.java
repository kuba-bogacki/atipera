package com.atipera.service;

import com.atipera.configuration.client.GitHubClient;
import com.atipera.model.dto.BranchDto;
import com.atipera.model.dto.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGitHubRepositoriesFetcherService implements GitHubRepositoriesFetcherService {

    private final GitHubClient gitHubClient;

    @Override
    public List<RepositoryDto> getUserRepositories(String username) {
        gitHubClient.validateUserExist(username);
        final var repositories = gitHubClient.fetchUserRepositories(username);

        return repositories.stream()
                .filter(repository -> !repository.isFork())
                .map(repository -> RepositoryDto.of(repository.getName(), repository.getOwner().getLogin(), getBranch(username, repository.getName())))
                .toList();
    }

    private List<BranchDto> getBranch(String username, String repositoryName) {
        var branches = gitHubClient.fetchRepositoryBranches(username, repositoryName);
        return branches.stream()
                .map(branch -> BranchDto.of(branch.getName(), branch.getCommit().getSha()))
                .toList();
    }
}
