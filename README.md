# GitHub Repository Fetcher

A Spring Boot application that lists non-forked GitHub repositories for a given user with their branches.

## Features

- Lists all non-forked repositories for a GitHub user
- For each repository, includes:
    - Repository name
    - Owner login
    - List of branches with their names and last commit SHA
- Proper error handling for non-existent users (404 response)

## 🛠 Technologies

- **Java 21**
- **Spring Boot 3.5**
- **Spring Web MVC**
- **Jackson** 
- **Lombok** 
- **JUnit 5**
- **AssertJ**

## API Endpoint

`GET /api/github/repositories/{username}`

### Successful Response Example

```json
[
    {
        "repositoryName": "my-repo",
        "ownerLogin": "octocat",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "abc123"
            },
            {
                "name": "dev",
                "lastCommitSha": "def456"
            }
        ]
    }
]
```

### Unsuccessful Response Example

```json
{
    "status": 404,
    "message": "GitHub user not found"
}
```