import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends AbstractCourierCreateTest {

    private final String order;

    public OrderCreateTest(String order) {
        this.order = order;
    }

    @Test
    public void orderCreateWithBodyTest() {
        Response response = postRequest(order, "/api/v1/orders");

        int track = response.then().assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue())
                .extract()
                .body()
                .path("track");

        cancelOrder(track);
    }

    protected Response cancelOrder(int track) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(String.format("{\"track\": %d}", track))
                .when()
                .put("/api/v1/orders/cancel");
    }

    @Parameterized.Parameters
    public static Object[] getCount() {
        return new Object[]{
                "{\n" +
                        "    \"firstName\": \"Naruto\",\n" +
                        "    \"lastName\": \"Uchiha\",\n" +
                        "    \"address\": \"Konoha, 142 apt.\",\n" +
                        "    \"metroStation\": 4,\n" +
                        "    \"phone\": \"+7 800 355 35 35\",\n" +
                        "    \"rentTime\": 5,\n" +
                        "    \"deliveryDate\": \"2020-06-06\",\n" +
                        "    \"comment\": \"Saske, come back to Konoha\",\n" +
                        "    \"color\": []\n" +
                        "}",
                "{\n" +
                        "    \"firstName\": \"Naruto\",\n" +
                        "    \"lastName\": \"Uchiha\",\n" +
                        "    \"address\": \"Konoha, 142 apt.\",\n" +
                        "    \"metroStation\": 4,\n" +
                        "    \"phone\": \"+7 800 355 35 35\",\n" +
                        "    \"rentTime\": 5,\n" +
                        "    \"deliveryDate\": \"2020-06-06\",\n" +
                        "    \"comment\": \"Saske, come back to Konoha\",\n" +
                        "    \"color\": [\n" +
                        "        \"BLACK\"\n" +
                        "    ]\n" +
                        "}",
                "{\n" +
                        "    \"firstName\": \"Naruto\",\n" +
                        "    \"lastName\": \"Uchiha\",\n" +
                        "    \"address\": \"Konoha, 142 apt.\",\n" +
                        "    \"metroStation\": 4,\n" +
                        "    \"phone\": \"+7 800 355 35 35\",\n" +
                        "    \"rentTime\": 5,\n" +
                        "    \"deliveryDate\": \"2020-06-06\",\n" +
                        "    \"comment\": \"Saske, come back to Konoha\",\n" +
                        "    \"color\": [\n" +
                        "        \"GREY\"\n" +
                        "    ]\n" +
                        "}",
                "{\n" +
                        "    \"firstName\": \"Naruto\",\n" +
                        "    \"lastName\": \"Uchiha\",\n" +
                        "    \"address\": \"Konoha, 142 apt.\",\n" +
                        "    \"metroStation\": 4,\n" +
                        "    \"phone\": \"+7 800 355 35 35\",\n" +
                        "    \"rentTime\": 5,\n" +
                        "    \"deliveryDate\": \"2020-06-06\",\n" +
                        "    \"comment\": \"Saske, come back to Konoha\",\n" +
                        "    \"color\": [\n" +
                        "        \"BLACK\",\n" +
                        "        \"GREY\"\n" +
                        "    ]\n" +
                        "}"
        };
    }
}
