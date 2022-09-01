import dto.Courier;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public abstract class AbstractCourierCreateTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    protected <T> Response postRequest(T json, String s) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(s);
    }
    protected void deleteCourier(Courier json) {
        Integer id = postRequest(json, "/api/v1/courier/login")
                .then().extract().body().path("id");

        given()
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/" + id)
                .then()
                .statusCode(200);
    }
}
