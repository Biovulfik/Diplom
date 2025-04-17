package ru.iteco.fmhandroid.ui.Test;

import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObjects.LoginPage;
import ru.iteco.fmhandroid.ui.PageObjects.MainPage;
import ru.iteco.fmhandroid.ui.Data.EspressoHelper;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private CountingIdlingResource idlingResource;
    private View decorView;

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        try {
            loginPage.waitForAuthPage();
        } catch (Exception e) {
            mainPage.openMenu();
            mainPage.clickLogout();
            loginPage.checkAuthPageIsDisplayed();
        }
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        registerIdlingResource();
    }

    @Before
    public void registerIdlingResource() {
        idlingResource = new CountingIdlingResource("SplashScreen");
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }



    @Test
    @Story("ТК-1 - Вход с корректными данными")
    public void successfulAuthorizationTest() {
        loginPage.performLogin(loginPage.getValidLogin(), loginPage.getValidPassword());
        mainPage.waitForMainPage();
    }

    @Test
    @Story("ТК-2 - Вход с пустым полем Логин")
    public void authorizationWithEmptyLoginTest() {
        loginPage.performLogin(loginPage.getEmptyField(), loginPage.getValidPassword());
        EspressoHelper.checkToastMessage(decorView,"Логин и пароль не могут быть пустыми");
    }

    @Test
    @Story("ТК-3 - Вход с пустым полем Пароль")
    public void authorizationWithEmptyPasswordTest() {
        loginPage.performLogin(loginPage.getValidLogin(), loginPage.getEmptyField());
        EspressoHelper.checkToastMessage(decorView,"Логин и пароль не могут быть пустыми");
    }

    @Test
    @Story("ТК-4 - Вход с логином состоящим из одного символа")
    public void loginWithOneCharacterInTheLoginFieldTest() {
        loginPage.performLogin(loginPage.getOneCharacter(), loginPage.getValidPassword());
        EspressoHelper.checkToastMessage(decorView,"Что-то пошло не так. Попробуйте позднее.");
    }

    @Test
    @Story("ТК-5 - Вход с паролем состоящим из одного символа")
    public void loginWithOneCharacterInThePasswordFieldTest() {
        loginPage.performLogin(loginPage.getValidLogin(), loginPage.getOneCharacter());
        EspressoHelper.checkToastMessage(decorView,"Что-то пошло не так. Попробуйте позднее.");
    }

    @Test
    @Story("ТК-6 - Вход с некорректным логином")
    public void loginWithIncorrectLoginTest() {
        loginPage.performLogin(loginPage.getInvalidLogin(), loginPage.getValidPassword());
        EspressoHelper.checkToastMessage(decorView,"Что-то пошло не так. Попробуйте позднее.");
    }

    @Test
    @Story("ТК-7 - Вход с некорректным паролем")
    public void loginWithIncorrectPasswordTest() {
        loginPage.performLogin(loginPage.getValidLogin(), loginPage.getInvalidPassword());
        EspressoHelper.checkToastMessage(decorView,"Что-то пошло не так. Попробуйте позднее.");
    }
}
