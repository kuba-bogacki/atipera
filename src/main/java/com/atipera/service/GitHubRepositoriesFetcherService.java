package com.atipera.service;

import com.atipera.model.dto.RepositoryDto;

import java.util.List;

public interface GitHubRepositoriesFetcherService {
    List<RepositoryDto> getUserRepositories(String username);
}
