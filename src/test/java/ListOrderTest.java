import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ListOrderTest extends AbstractCourierCreateTest{
    @Test
    public void listOrder(){
         given()
                .header("Content-type", "application/json")
               // .and()
                .when()
                .get("/api/v1/orders")
                 .then().assertThat()
                 .statusCode(200)
                 .and()
                 .body("orders", notNullValue())
                 .and()
                 .body("pageInfo",notNullValue())
                 .and()
                 .body("availableStations",notNullValue());
    }
}
