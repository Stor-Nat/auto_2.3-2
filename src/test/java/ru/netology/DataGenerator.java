package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("en"));
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void registerUser(RegistrationPage registrationPage) {
        given()
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(registrationPage) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    static String getLogin() {
        return faker.name().firstName();
    }

    static String getPassword() {
        return faker.name().firstName();
    }

    public static RegistrationPage getUserActive() {
        RegistrationPage user = new RegistrationPage(getLogin(), getPassword(), "active");
        registerUser(user);
        return user;
    }

    public static RegistrationPage getUserBlocked() {
        RegistrationPage user = new RegistrationPage(getLogin(), getPassword(), "blocked");
        registerUser(user);
        return user;
    }

    public static RegistrationPage getWrongLoginUser() {
        String login = getLogin();
        RegistrationPage user = new RegistrationPage(login, getPassword(), "active");
        registerUser(user);
        return new RegistrationPage(login, getPassword(), "active");
    }

    public static RegistrationPage getWrongPasswordUser() {
        String password = getPassword();
        RegistrationPage user = new RegistrationPage(getLogin(), password, "active");
        registerUser(user);
        return new RegistrationPage(getLogin(), password, "active");
    }

    public static RegistrationPage getNotRegisteredUser() {
        return new RegistrationPage(getLogin(), getPassword(), "active");
    }

}

