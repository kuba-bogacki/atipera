package com.atipera.controller;

import com.atipera.service.GitHubRepositoriesFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1.0/repositories")
@RequiredArgsConstructor
public class GitHubRepositoriesFetcherController {

    private final GitHubRepositoriesFetcherService fetcherService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserRepositories(@PathVariable String username) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetcherService.getUserRepositories(username));
    }
}
