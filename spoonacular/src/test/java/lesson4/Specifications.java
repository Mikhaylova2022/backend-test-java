package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static lesson4.AbstractTest.*;

public class Specifications {
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .setContentType(ContentType.JSON)
                .setBaseUri(getBaseUrl())
                .build();
    }
    public static RequestSpecification requestSpecUnAuth() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(getBaseUrl())
                .build();
    }

    public static RequestSpecification requestSpecWithHash() {
        return new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .addQueryParam("hash", getHash())
                .setContentType(ContentType.JSON)
                .setBaseUri(getBaseUrl())
                .build();
    }

    public static ResponseSpecification responseSpecUnique(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
