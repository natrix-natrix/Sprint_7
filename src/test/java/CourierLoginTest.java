import dto.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends AbstractCourierCreateTest {


    @Test
    @DisplayName("Авторизация курьера")
    @Description("курьер может авторизоваться\n" +
            "для авторизации нужно передать все обязательные поля\n" +
            "успешный запрос возвращает id")
    public void courierAuthorisationTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Response response = postRequest(json, "/api/v1/courier/login");
        deleteCourier(json);
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера c невалидным логином")
    @Description("система вернёт ошибку, если неправильно указать логин")
    public void courierAuthorisationInvalidLoginTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Courier login = new Courier()
                .setLogin("ninjalg")
                .setPassword("1234");
        Response response = postRequest(login, "/api/v1/courier/login");
        deleteCourier(json);
        response.then().assertThat()
                .statusCode(404)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера c невалидным паролем")
    @Description("система вернёт ошибку, если неправильно указать пароль")
    public void courierAuthorisationInvalidPasswordTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Courier login = new Courier()
                .setLogin("ninjalga")
                .setPassword("123");
        Response response = postRequest(login, "/api/v1/courier/login");
        deleteCourier(json);
        response.then().assertThat()
                .statusCode(404)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("если какого-то поля нет, запрос возвращает ошибку")
    public void courierAuthorisationNullPasswordTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Courier login = new Courier()
                .setLogin("ninjalga");
        Response response = postRequest(login, "/api/v1/courier/login");
        deleteCourier(json);
        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("если какого-то поля нет, запрос возвращает ошибку")
    public void courierAuthorisationNullLogin() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        Courier login = new Courier()
                .setPassword("1234");
        Response response = postRequest(login, "/api/v1/courier/login");
        deleteCourier(json);
        response.then().assertThat()
                .statusCode(400)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void courierAuthorisationNotExistentTest() {
        Courier json = new Courier()
                .setLogin("ninjalga")
                .setPassword("1234");
        postRequest(json, "/api/v1/courier");
        deleteCourier(json);
        Response response = postRequest(json, "/api/v1/courier/login");
        response.then().assertThat()
                .statusCode(404)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }
}
