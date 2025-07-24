package com.atipera.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryDto {

    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;

    public static RepositoryDto of(String repositoryName, String ownerLogin, List<BranchDto> branches) {
        requireNonNull(repositoryName, "Repository name must not be null");
        requireNonNull(ownerLogin, "Owner login must not be null");
        requireNonNull(branches, "Branches must not be null");
        return new RepositoryDto(repositoryName, ownerLogin, branches);
    }
}
