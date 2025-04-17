package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObjects.AboutPage;
import ru.iteco.fmhandroid.ui.PageObjects.LoginPage;
import ru.iteco.fmhandroid.ui.PageObjects.MainPage;
import ru.iteco.fmhandroid.ui.PageObjects.MenuPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTheApplicationTest {

    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule(AppActivity.class);

    private MainPage mainPage = new MainPage();
    private MenuPage menuPage = new MenuPage();
    private AboutPage aboutPage = new AboutPage();
    private LoginPage loginPage = new LoginPage();

    @Before
    public void setUp() {
        try {
            mainPage.waitForMainPage();
        } catch (Exception e) {
            loginPage.waitForAuthPage();
            loginPage.performLogin(loginPage.getValidLogin(), loginPage.getInvalidPassword());
            mainPage.waitForMainPage();
        }
    }

    @Test
    @Story("ТК-53 - Переход по ссылке Политика конфиденциальности")
    public void switchToPrivacyPolicy() {
        mainPage.openMainMenu();
        menuPage.selectAbout();
        aboutPage.clickPrivacyPolicyLink();
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://vhospice.org/#/privacy-policy")));
    }

    @Test
    @Story("ТК-54 - Переход по ссылке Пользовательское соглашение")
    public void transitionToUserAgreement() {
        mainPage.openMainMenu();
        menuPage.selectAbout();
        aboutPage.clickTermsOfUseLink();
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://vhospice.org/#/terms-of-use")));
    }
}
