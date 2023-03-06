package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestSuite extends AbstractTest {

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    void testSuite1() {

        //метод addMeal
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
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        //метод deleteFromMealPlan
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .delete(getBaseUrl() + "mealplanner/" + getUsername() + "/items/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    void testSuite2() {

        //метод addToShoppingList: добавляем vanilla cream
        String id = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .body("{\n"
                        + " \"item\": \"1 package vanilla cream\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + " \"parse\":  true,\n"
                        + "}")
                .expect()
                .body("name", equalTo("vanilla cream"))
                .body("aisle", equalTo("Baking"))
                .when()
                .post(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

    //метод deleteFromShoppingList: удаляем vanilla cream
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .expect()
                .body("status", equalTo("success"))
                .when()
                .delete(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list/items/" + id)
                .then()
                .statusCode(200);

    //метод getShoppingList: проверяем что vanilla cream удален
        List<Object> names = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", getHash())
                .when()
                .get(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("aisles.items.name");

        assert !names.toString().contains("vanilla cream");
    }


}
