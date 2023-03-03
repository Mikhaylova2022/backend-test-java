package lesson3;


import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class GET extends AbstractTest{
    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    void getSearchResipeSuccessTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "salad")
                .queryParam("cuisine", "European")
                .queryParam("excludeIngredients", "Yogurt", "pork")
                .queryParam("includeIngredients", "chiken")
                .log().method()
                .log().params()
                .expect()
                .body("results.id[0]", equalTo(653098))
                .body("results.title[0]", equalTo("Niçoise Salad"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void getRecipeWithBodyChecksInGivenPositiveTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .expect()
                .body("vegetarian", is(false))
                .body("vegan", is(false))
                .body("license", equalTo("CC BY-SA 3.0"))
                .body("pricePerServing", equalTo(163.15F))
                .body("extendedIngredients[0].aisle", equalTo("Milk, Eggs, Other Dairy"))
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");
    }
    @Test
    void getSearchResipeUnauthorizedTest() {
        given()
                .queryParam("query", "salad")
                .queryParam("cuisine", "European")
                .queryParam("diet", "Whole30")
                .queryParam("excludeIngredients", "chicken", " cheese", "lettuce")
                .log().method()
                .log().params()
                .expect()
                .body("status", equalTo("failure"))
                .body("message", containsString("You are not authorized"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(401);
    }

    @Test
    void getSearchResipeEmptyResponseTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", false)
                .log().method()
                .log().params()
                .expect()
                .body("totalResults", equalTo(0))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }
    @Test
    void generateMealPlanTest() {

        Float calories = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "Ketogenic")
                .queryParam("exclude", "shellfish")
                .when()
                .get(getBaseUrl() + "mealplanner/generate")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("nutrients.calories");

        assert calories < 2000.00F;
    }

}