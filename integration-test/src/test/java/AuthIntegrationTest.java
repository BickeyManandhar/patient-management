import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOKWithValidToken() {
        // 1. Arrange, 2. Act and 3. Assert

        String loginPayload = """
                {
                    "email": "bickey@gmail.com",
                    "password": "password123"
                }
                """;

        //api-gateway service -> auth-service ->patient-service
        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();
        System.out.println("Generated Token: " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin() {
        // 1. Arrange, 2. Act and 3. Assert

        String loginPayload = """
                {
                    "email": "invalid_user@gmail.com",
                    "password": "invalid-password"
                }
                """;

        //there is not going to be any response
        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);

    }
}
