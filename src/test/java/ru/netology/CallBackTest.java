package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.*;

public class CallBackTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnMessageUserActive() {
        RegistrationPage validUser = getUserActive();
        $("[data-test-id='login'] input").setValue(validUser.getLogin());
        $("[data-test-id='password'] input").setValue(validUser.getPassword());
        $(byText("Продолжить")).click();
        $("h2").shouldHave(exactText("Личный кабинет"));
    }

    @Test
    public void shouldReturnMessageUserBlocked() {
        RegistrationPage blockedUser = getUserBlocked();
        $("[data-test-id='login'] input").setValue(blockedUser.getLogin());
        $("[data-test-id='password'] input").setValue(blockedUser.getPassword());
        $(byText("Продолжить")).click();
        $(".notification__content").shouldHave(exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    public void shouldReturnMessageWrongLoginUser() {
        RegistrationPage invalidLoginUser= getWrongLoginUser();
        $("[data-test-id='login'] input").setValue(invalidLoginUser.getLogin());
        $("[data-test-id='password'] input").setValue(invalidLoginUser.getPassword());
        $(byText("Продолжить")).click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    public void shouldReturnMessageWrongPasswordUser() {
        RegistrationPage invalidPasswordUser = getWrongPasswordUser();
        $("[data-test-id='login'] input").setValue(invalidPasswordUser.getLogin());
        $("[data-test-id='password'] input").setValue(invalidPasswordUser.getPassword());
        $(byText("Продолжить")).click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    public void shouldReturnMessageNotRegisteredUser() {
        RegistrationPage userNotRegistered = getNotRegisteredUser();
        $("[data-test-id='login'] input").setValue(userNotRegistered.getLogin());
        $("[data-test-id='password'] input").setValue(userNotRegistered.getPassword());
        $(byText("Продолжить")).click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }
}
