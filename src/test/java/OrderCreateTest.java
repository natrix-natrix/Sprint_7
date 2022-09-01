import dto.Order;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends AbstractCourierCreateTest {

    private final Order order;

    public OrderCreateTest(List<String> color) {
        this.order = new Order()
                .setFirstName("Naruto")
                .setLastName("Uchiha")
                .setAddress("Konoha, 142 apt.")
                .setMetroStation(4)
                .setPhone("+7 800 355 35 35")
                .setRentTime(5)
                .setDeliveryDate(LocalDate.now())
                .setComment("Saske, come back to Konoha")
                .setColor(color);
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
                Collections.emptyList(),
                List.of("BLACK"),
                List.of("GREY"),
                List.of("BLACK", "GREY")};
    }
}
