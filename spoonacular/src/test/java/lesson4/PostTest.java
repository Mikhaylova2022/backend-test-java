package lesson4;

import lesson4.api.ConnectUser;
import lesson4.response.ConnectUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostTest extends AbstractTest {

    @BeforeEach
    public void configureSpecifications() {
        Specifications.installSpecifications(Specifications.requestSpecification(), Specifications.responseSpecUnique(200));
    }

    @Test
    void connectUserTest() {
        ConnectUser user = new ConnectUser("batsenkova", "tatiana", "batsenkova", "newbox@bk.ru");
        ConnectUserResponse connectUser =
                given()
                .body(user)
                .expect()
                .body("status", equalTo("success"))
                .when()
                .post(getBaseUrl() + "users/connect")
                .then()
                .extract()
                .body().as(ConnectUserResponse.class);
        assertThat(connectUser.getStatus(), equalTo("success"));
        System.out.println(connectUser.getHash());
    }

}
