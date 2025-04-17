package ru.iteco.fmhandroid.ui.Test;

import android.view.View;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
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
import ru.iteco.fmhandroid.ui.PageObjects.NewsCreationPage;
import ru.iteco.fmhandroid.ui.PageObjects.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsCreationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private CountingIdlingResource idlingResource;
    private View decorView;

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private NewsPage newsPage = new NewsPage();
    private NewsCreationPage newsCreationPage = new NewsCreationPage();

    @Before
    public void setUp() {
        try {
            mainPage.waitForMainPage();
        } catch (Exception e) {
            loginPage.waitForAuthPage();
            loginPage.performLogin(loginPage.getValidLogin(), loginPage.getValidPassword());
            mainPage.waitForMainPage();
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
    @Story("ТК-41 - Создание новости только с заполненным полем Категория")
    public void filledCategoryFieldOnly() {
        mainPage.buttonAllNews();
        newsPage.waitUntilLoaded();
        newsPage.buttonControlPanel();
        newsPage.ButtonAddNews();
        newsCreationPage.selectCategory(newsCreationPage.getAnnouncement());
        newsCreationPage.saveNews();
        newsCreationPage.verifyErrorMessage("Заполните пустые поля", decorView);
    }

    @Test
    @Story("ТК-42 - Создание новости с вводом категории вручную")
    public void enteringCategoryManually() {
        mainPage.buttonAllNews();
        newsPage.waitUntilLoaded();
        newsPage.buttonControlPanel();
        newsPage.ButtonAddNews();
        newsCreationPage.enterCategoryManually("Распродажа");
        newsCreationPage.newsHeadline("Шубы");
        newsCreationPage.setCurrentDate();
        newsCreationPage.setCurrentTime();
        newsCreationPage.enterDescription("Очень дешево");
        newsCreationPage.saveNews();
        newsCreationPage.verifyErrorMessage("Сохранение не удалось. Попробуйте позднее.", decorView);
    }

    @Test
    @Story("ТК-43 - Создание новости в категории Объявление")
    public void creatingNewsInTheCategoryAnnouncement() {
        mainPage.buttonAllNews();
        newsPage.waitUntilLoaded();
        newsPage.buttonControlPanel();
        newsPage.ButtonAddNews();
        newsCreationPage.selectCategory(newsCreationPage.getAnnouncement());
        newsCreationPage.newsHeadline("Машина");
        newsCreationPage.setCurrentDate();
        newsCreationPage.setCurrentTime();
        newsCreationPage.enterDescription("Очень дешево");
        newsCreationPage.saveNews();
        newsPage.sortNews();
        newsPage.verifyNewsExists("Машина");
    }

    @Test
    @Story("ТК-44 - Создание новости в категории День рождения с вводом времени публикации вручную")
    public void enteringPublicationTimeManually() {
        mainPage.buttonAllNews();
        newsPage.waitUntilLoaded();
        newsPage.buttonControlPanel();
        newsPage.ButtonAddNews();
        newsCreationPage.selectCategory(newsCreationPage.getBirthday());
        newsCreationPage.newsHeadline("У меня");
        newsCreationPage.setCurrentDate();
        newsCreationPage.setTimeManually(12, 30);
        newsCreationPage.enterDescription("Жду в гости");
        newsCreationPage.saveNews();
        newsPage.sortNews();
        newsPage.verifyNewsExists("У меня");
    }
}
