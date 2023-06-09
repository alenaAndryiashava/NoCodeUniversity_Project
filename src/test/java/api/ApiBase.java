package api;

import com.github.javafaker.Faker;
import api.enums.EndPoint;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class ApiBase {
    final String SOFTR_API_KEY = "khIbAyJIU5CIuh1oDuBRx1s49";
    final String CURL = "https://studio-api.softr.io/v1";
    final String SOFTR_DOMAIN = "jere237.softr.app";
    final String NEW_SOFTR_DOMAIN = "erich416.softr.app";

    protected Faker faker = new Faker();

    @BeforeMethod
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(CURL)
            .setContentType(ContentType.JSON)
            .addHeader("Softr-Api-Key", SOFTR_API_KEY)
            .addHeader("Softr-Domain", NEW_SOFTR_DOMAIN)
            .build();

    public Response doPostRequest(EndPoint endPoint, int statusCode, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public Response doDeleteRequest(EndPoint endPoint, int statusCode, String email) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParams("email", email)
                .log().all()
                .delete(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public String getWrongEmail() {
        return faker.internet().emailAddress();
    }
}
