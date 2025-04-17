package ru.iteco.fmhandroid.ui.Test;

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

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class HomeTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    @Before
    public void setUp() {
        try {
            mainPage.waitForMainPage();
        } catch (Exception e) {
            loginPage.waitForAuthPage();
            loginPage.performLogin(loginPage.getValidLogin(), loginPage.getValidPassword());
            mainPage.waitForMainPage();
        }
    }


    @Test
    @Story("ТК-8 - Отображение списка новостей")
    public void displayingListOfNewsTest() {
        mainPage.checkNewsListIsDisplayed();
    }

    @Test
    @Story("ТК-9 - Свернуть/развернуть полный список новостей")
    public void collapseExpandNewsList() {
        mainPage.clickExpandNewsButton();
        mainPage.clickExpandNewsButton();
        mainPage.checkAllNewsTitleIsDisplayed();
    }
}
