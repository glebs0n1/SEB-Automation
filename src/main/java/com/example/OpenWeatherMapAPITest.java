package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenWeatherMapAPITest {
    private final String API_KEY = "your_api_key_here";
    private final String BASE_URI = "http://api.openweathermap.org/data/2.5";
    private final String WEATHER_PATH = "/weather";
    private final String CITY_PARAM = "q";
    private final String API_KEY_PARAM = "appid";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void testWeatherAPI() {
        Response response = given()
                .param(CITY_PARAM, "Vilnius")
                .param(API_KEY_PARAM, API_KEY)
                .get(WEATHER_PATH);

        assertEquals(200, response.getStatusCode(), "Status code is not 200");
    }
}
