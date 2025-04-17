package ru.iteco.fmhandroid.ui.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObjects.LoginPage;
import ru.iteco.fmhandroid.ui.PageObjects.MainPage;
import ru.iteco.fmhandroid.ui.PageObjects.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotesTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private QuotesPage quotesPage = new QuotesPage();

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
    @Story("ТК-13 - Открытие цитат")
    public void LoadingPageWithQuotes() {
        mainPage.butterflyButton();
        quotesPage.checkLoadingPageQuotes();
    }

    @Test
    @Story("ТК-16 - Свернуть/развернуть цитату")
    public void collapseExpandQuote() {
        mainPage.butterflyButton();
        quotesPage.checkLoadingPageQuotes();
        quotesPage.expandQuote("Хоспис для меня");
        quotesPage.checkQuoteDescriptionVisible("идеальное устройство мира");
    }
}

