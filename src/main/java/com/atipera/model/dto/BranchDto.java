package com.atipera.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchDto {

    private final String branchName;
    private final String lastCommitSha;

    public static BranchDto of(String branchName, String lastCommitSha) {
        requireNonNull(branchName, "Branch name must not be null");
        requireNonNull(lastCommitSha, "Branch last commit sha must not be null");
        return new BranchDto(branchName, lastCommitSha);
    }
}
