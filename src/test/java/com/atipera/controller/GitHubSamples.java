package com.atipera.controller;

import com.sun.net.httpserver.HttpExchange;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.OutputStream;
import java.util.List;

class GitHubSamples {

    final static String contentType = "Content-Type";
    final static String applicationJson = "application/json";

    final static String username = "kuba-bogacki";
    final static String userUrl = "/users/kuba-bogacki";
    final static String repositoriesUrl = "/users/kuba-bogacki/repos";
    final static String askMateBranchUrl = "/repos/kuba-bogacki/ask-mate/branches";
    final static String carMarketBackendBranchUrl = "/repos/kuba-bogacki/car-market-backend/branches";
    final static String cardCrawlBranchUrl = "/repos/kuba-bogacki/card-crawl/branches";

    final static String gitHubUrlPropertyName = "github.api.url";
    final static String defaultLocalHostUri = "http://localhost:";
    final String endpoint = "http://localhost:%s/v1.0/repositories/%s";

    @SneakyThrows
    static void createResponse(String response, HttpExchange exchange) {
        exchange.getResponseHeaders().set(contentType, applicationJson);
        exchange.sendResponseHeaders(HttpStatus.OK.value(), response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    HttpHeaders createHeaders() {
        var headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    final static String userResponse = """
            {
                "login": "kuba-bogacki",
                "id": 25335969,
                "avatar_url": "https://avatars.githubusercontent.com/u/82802969?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/kuba-bogacki",
                "html_url": "https://github.com/kuba-bogacki"
            }
            """;

    final static String repositoriesResponse = """
            [
                {
                    "id": 510819587,
                    "name": "ask-mate",
                    "full_name": "kuba-bogacki/ask-mate",
                    "private": false,
                    "owner": {
                        "login": "kuba-bogacki",
                        "avatar_url": "https://avatars.githubusercontent.com/u/82802969?v=4",
                        "site_admin": false
                    },
                    "fork": false
                },
                {
                    "id": 551570384,
                    "name": "car-market-backend",
                    "full_name": "kuba-bogacki/car-market-backend",
                    "private": false,
                    "owner": {
                        "login": "kuba-bogacki",
                        "avatar_url": "https://avatars.githubusercontent.com/u/82802969?v=4",
                        "site_admin": false
                    },
                    "fork": false
                },
                {
                    "id": 510820035,
                    "name": "card-crawl",
                    "full_name": "kuba-bogacki/card-crawl",
                    "private": false,
                    "owner": {
                        "login": "kuba-bogacki",
                        "avatar_url": "https://avatars.githubusercontent.com/u/82802969?v=4",
                        "site_admin": false
                    },
                    "fork": false
                }
            ]
            """;

    final static String askMateBranchResponse = """
            [
                {
                    "name": "main",
                    "commit": {
                        "sha": "6dcb09b5b57875f334f61a24ed695e2e4193db5e"
                        }
                },
                {
                    "name": "develop",
                    "commit": {
                        "sha": "5e5b5e5a5e5b5e5a55e5b5e5a5e5b5e5a55e5b5v"
                        }
                }
            ]
            """;

    final static String defaultBranchResponse = """
            []
            """;

    final String endpointResult =
            "[" +
                "{" +
                    "\"repositoryName\":\"ask-mate\"," +
                    "\"ownerLogin\":\"kuba-bogacki\"," +
                    "\"branches\":[" +
                        "{" +
                            "\"branchName\":\"main\"," +
                            "\"lastCommitSha\":\"6dcb09b5b57875f334f61a24ed695e2e4193db5e\"" +
                        "}," +
                        "{" +
                            "\"branchName\":\"develop\"," +
                            "\"lastCommitSha\":\"5e5b5e5a5e5b5e5a55e5b5e5a5e5b5e5a55e5b5v\"" +
                        "}" +
                    "]" +
                "}," +
                "{" +
                    "\"repositoryName\":\"car-market-backend\"," +
                    "\"ownerLogin\":\"kuba-bogacki\"," +
                    "\"branches\":[]" +
                "}," +
                "{" +
                    "\"repositoryName\":\"card-crawl\"," +
                    "\"ownerLogin\":\"kuba-bogacki\"," +
                    "\"branches\":[]" +
                "}" +
            "]";
}
