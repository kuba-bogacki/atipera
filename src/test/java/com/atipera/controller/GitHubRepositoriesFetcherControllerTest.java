package com.atipera.controller;

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.net.InetSocketAddress;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "github.api.url=http://localhost:${wiremock.server.port}")
class GitHubRepositoriesFetcherControllerTest extends GitHubSamples {

    private static HttpServer SERVER;

    @LocalServerPort
    private int applicationPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    @SneakyThrows
    static void setUp() {
        SERVER = HttpServer.create(new InetSocketAddress(0), 0);
        SERVER.createContext(userUrl, exchange -> createResponse(userResponse, exchange));
        SERVER.createContext(repositoriesUrl, exchange -> createResponse(repositoriesResponse, exchange));
        SERVER.createContext(askMateBranchUrl, exchange -> createResponse(askMateBranchResponse, exchange));
        SERVER.createContext(carMarketBackendBranchUrl, exchange -> createResponse(defaultBranchResponse, exchange));
        SERVER.createContext(cardCrawlBranchUrl, exchange -> createResponse(defaultBranchResponse, exchange));
        SERVER.start();
    }

    @DynamicPropertySource
    static void overrideGitHubApiUrl(DynamicPropertyRegistry registry) {
        registry.add(gitHubUrlPropertyName, () -> defaultLocalHostUri + SERVER.getAddress().getPort());
    }

    @AfterAll
    static void tearDown() {
        SERVER.stop(0);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return valid response if user with provided username exist")
    void test_01() {
        //given
        var url = String.format(endpoint, applicationPort, username);
        var headers = createHeaders();

        //when
        final var responseResult = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        //then
        assertAll(
                () -> assertThat(responseResult.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(responseResult.getBody()).isEqualTo(endpointResult)
        );
    }
}