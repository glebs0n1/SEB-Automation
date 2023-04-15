package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenWeatherMapAPITestTest {

    private static final String API_KEY = "your-api-key-here";

    @BeforeAll
    public static void setup() {
        // Set the base URL for all REST API requests
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5";
    }

    @Test
    public void testGetCurrentWeatherByCityName() {
        // Send a GET request to the current weather endpoint with city name as the parameter
        Response response = RestAssured.given()
                .queryParam("q", "Vilnius")
                .queryParam("appid", API_KEY)
                .get("/weather");

        // Verify the response status code is 200 OK
        assertThat(response.getStatusCode()).isEqualTo(200);

        // Verify the "name" field in the response body is "Vilnius"
        assertThat(response.jsonPath().get("name")).isEqualTo("Vilnius");
    }


    @Test
    public void testGetInvalidAPIKey() {
        // Send a GET request to the current weather endpoint with an invalid API key
        Response response = RestAssured.given()
                .queryParam("q", "Vilnius")
                .queryParam("appid", "invalid-key")
                .get("/weather");

        // Verify the response status code is 401 Unauthorized
        assertThat(response.getStatusCode()).isEqualTo(401);
    }
}