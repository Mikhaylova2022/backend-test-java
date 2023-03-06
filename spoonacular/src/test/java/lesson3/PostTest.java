package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostTest extends AbstractTest {
    @Test
    void connectUserTest() {
        String hash = given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"username\": \"batsenkova\",\n"
                        + " \"firstName\": \"tatiana\",\n"
                        + " \"lastName\": \"batsenkova\",\n"
                        + " \"email\": \"newbox@bk.ru\",\n"
                        + "}")
                .expect()
                .body("status", equalTo("success"))
                .when()
                .post(getBaseUrl() + "users/connect")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("hash")
                .toString();
    }

    @Test
    void addMealTest() {
        String id = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post(getBaseUrl() + "mealplanner/" + getUsername() + "/items")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .log().all()
                .delete(getBaseUrl() + "mealplanner/" + getUsername() + "/items/" + id)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void generateShoppingListTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .when()
                .post(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list/2023-02-25/2023-03-01")
                .then().statusCode(200);
    }

    @Test
    void computeShoppingListTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .log().method()
                .log().body()
                .body("{\n"
                        + " \"items\": [ \n"
                        + "\"4 lbs tomatoes\",\n"
                        + " \"10 tomatoes\",\n"
                        + " \"20 Tablespoons Olive Oil\",\n"
                        + " \"6 tbsp Olive Oil\"\n"
                        + "]\n"
                        + "}")
                .expect()
                .body("cost", equalTo(1326.62F))
                .when()
                .post(getBaseUrl() + "mealplanner/shopping-list/compute")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
