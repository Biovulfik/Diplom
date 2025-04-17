package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.EspressoHelper;

public class LoginPage {
    private static final String DEFAULT_VALID_LOGIN = "login2";
    private static final String DEFAULT_VALID_PASSWORD = "password2";
    private static final String DEFAULT_INVALID_LOGIN = "loguna";
    private static final String DEFAULT_INVALID_PASSWORD = "passaj";
    private static final String EMPTY_FIELD = " ";
    private static final String ONE_CHARACTER = "f";

    public String getValidLogin() {
        return DEFAULT_VALID_LOGIN;
    }

    public String getValidPassword() {
        return DEFAULT_VALID_PASSWORD;
    }

    public String getInvalidLogin() {
        return DEFAULT_INVALID_LOGIN;
    }

    public String getInvalidPassword() {
        return DEFAULT_INVALID_PASSWORD;
    }

    public String getEmptyField() {
        return EMPTY_FIELD;
    }

    public String getOneCharacter() {
        return ONE_CHARACTER;
    }


    public void waitForAuthPage() {
        Allure.step("Ожидание страницы авторизации");
        EspressoHelper.waitForView(R.id.login_text_input_layout);
    }


    public void performLogin(String login, String password) {
        Allure.step("Авторизация");
        enterLogin(login);
        enterPassword(password);
        clickSignInButton();
    }


    public void enterLogin(String login) {
        Allure.step("Ввод логина");
        onView(allOf(
                isDescendantOfA(withId(R.id.login_text_input_layout)),
                withClassName(is("com.google.android.material.textfield.TextInputEditText")),
                isDisplayed()
        )).perform(replaceText(login), closeSoftKeyboard());
    }


    public void enterPassword(String password) {
        Allure.step("Ввод пароля");
        onView(allOf(
                isDescendantOfA(withId(R.id.password_text_input_layout)),
                withClassName(is("com.google.android.material.textfield.TextInputEditText")),
                isDisplayed()
        )).perform(replaceText(password), closeSoftKeyboard());
    }


    public void clickSignInButton() {
        Allure.step("Нажатие кнопки Войти");
        onView(allOf(
                withId(R.id.enter_button),
                withText("Войти"),
                isDisplayed()
        )).perform(click());
    }


    public void checkAuthPageIsDisplayed() {
        Allure.step("Проверка отображения страницы авторизации");
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
    }

}
