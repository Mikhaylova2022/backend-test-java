package lesson4;

import io.restassured.response.Response;
import lesson4.request.SearchRecipes;
import lesson4.response.GenerateMealPlan;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetTest extends AbstractTest {
    @Test
    void getSearchRecipesSuccessTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecUnique(200));
        SearchRecipes recipes = given()
                .queryParam("query", "salad")
                .queryParam("cuisine", "European")
                .queryParam("excludeIngredients", "Yogurt", "pork")
                .queryParam("includeIngredients", "chiken")
                .when()
                .get("recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body().as(SearchRecipes.class);
        assertThat(recipes.getNumber(), equalTo(10));
        assertThat(recipes.getOffset(), equalTo(0));
        assertThat(recipes.getTotalResults(), equalTo(15));
        assertThat(recipes, hasProperty("results"));
    }

    @Test
    void getSearchRecipesUnauthorizedTest() {
        Specifications.installSpecifications(Specifications.requestSpecUnAuth(), Specifications.responseSpecUnique(401));
        Response searchRecipes = given()
                .queryParam("query", "salad")
                .queryParam("cuisine", "European")
                .queryParam("diet", "Whole30")
                .queryParam("excludeIngredients", "chicken", " cheese", "lettuce")
                .get("recipes/complexSearch")
                .then()
                .body("status", equalTo("failure"))
                .body("message", containsString("You are not authorized"))
                .extract().response();

    }

    @Test
    void getSearchRecipesEmptyResponseTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecUnique(200));
        Response searchRecipes = given()
                .queryParam("titleMatch", false)
                .get("recipes/complexSearch")
                .then()
                .body("totalResults", equalTo(0))
                .extract().response();
    }

    @Test
    void generateMealPlanTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecUnique(200));
        GenerateMealPlan mealPlan = given()
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", "2000")
                .queryParam("diet", "Ketogenic")
                .queryParam("exclude", "shellfish")
                .when()
                .get("mealplanner/generate")
                .then()
                .extract()
                .response()
                .body()
                .as(GenerateMealPlan.class);
        assertThat(mealPlan.nutrients.getCalories(), closeTo(2000.00, 0.99));
    }
}
