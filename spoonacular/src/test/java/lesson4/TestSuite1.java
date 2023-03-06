package lesson4;

import io.restassured.response.Response;
import lesson4.api.AddToMeal;
import lesson4.api.Ingredient;
import lesson4.api.Value;
import lesson4.response.AddToMealResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSuite1 extends AbstractTest{
    @BeforeEach
    public void configureSpecifications() {
        Specifications.installSpecifications(Specifications.requestSpecWithHash(), Specifications.responseSpecUnique(200));
    }

    @Test
    void addToMealTest() {
        AddToMeal meal = new AddToMeal(1644881179, 1, 0, "INGREDIENTS",
                new Value(new ArrayList<Ingredient>(Collections.singletonList(new Ingredient("1 banana")))));
        AddToMealResponse response =
                given()
                        .pathParam("username", getUsername())
                        .body(meal)
                        .when()
                        .post("mealplanner/{username}/items")
                        .then()
                        .extract()
                        .body()
                        .as(AddToMealResponse.class);
        assertThat(response.getStatus(), equalTo("success"));

        /** Без POJO класса, тк проверяем только статус**/
        Response deleteItemFromMealPlan = given()
                .pathParam("id", response.getId())
                .pathParam("username", getUsername())
                .delete("mealplanner/{username}/items/{id}")
                .then()
                .body("status", equalTo("success"))
                .extract().response();
    }

}
