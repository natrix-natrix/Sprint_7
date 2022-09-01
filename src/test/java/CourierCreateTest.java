import dto.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class CourierCreateTest extends AbstractCourierCreateTest {


    @Test
    @DisplayName("Создание курьера")
    @Description("курьера можно создать\n" +
            "запрос возвращает правильный код ответа\n" +
            "успешный запрос возвращает ok: true")
    public void createCourier() {
        Courier json = new Courier()
                .setFirstName("saske")
                .setLogin("ninjalga")
                .setPassword("1234");
        Response response = postRequest(json, "/api/v1/courier");
        deleteCourier(json);

        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", is(true));

    }

    @Test
    @DisplayName("Создание одинаковых курьеров")
    @Description("нельзя создать двух одинаковых курьеров\n" +
            "если создать пользователя с логином, который уже есть, возвращается ошибка")
    public void createDuplicateCourier() {
        Courier json = new Courier()
                .setFirstName("saske")
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Response response = postRequest(json, "/api/v1/courier");
        deleteCourier(json);

        response.then().assertThat()
                .statusCode(409)
                .and()
                .body("message", is("Этот логин уже используется"));

    }
    @DisplayName("Проверка обязательности поля логин")
    @Test
    public void requiredFieldLoginTest() {
        Courier json = new Courier()
                .setFirstName("saske")
                .setPassword("1234");

        Response response =
                postRequest(json, "/api/v1/courier");

        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));

    }

    @DisplayName("Проверка обязательности поля пароль")
    @Test
    public void requiredFieldPasswordTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setFirstName("saske");

        Response response =
                postRequest(json, "/api/v1/courier");

        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));

    }

    @DisplayName("Проверка обязательности только полей логин и пароль")
    @Test
    public void requiredFieldOnlyTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");

        Response response = postRequest(json, "/api/v1/courier");
        deleteCourier(json);

        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", is(true));

    }


}
