package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.action.ViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.EspressoHelper;

public class MainPage {

    public void waitForMainPage() {
        Allure.step("Ожидание загрузки главной страницы");
        EspressoHelper.waitForView(R.id.trademark_image_view);
    }


    public void openMenu() {
        Allure.step("Открытие меню выхода");
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }


    public void clickLogout() {
        Allure.step("Нажатие кнопки Выйти");
        EspressoHelper.waitForView(android.R.id.title);
        onView(allOf(withId(android.R.id.title), withText("Выйти")))
                .perform(click());
    }


    public void checkNewsListIsDisplayed() {
        Allure.step("Проверка отображения списка новостей");
        onView(withId(R.id.container_list_news_include_on_fragment_main))
                .check(matches(isDisplayed()));
    }


    public void clickExpandNewsButton() {
        Allure.step("Нажатие на кнопку свернуть/развернуть новости");
        onView(allOf(
                withId(R.id.expand_material_button),
                isDescendantOfA(withId(R.id.container_list_news_include_on_fragment_main)),
                isDisplayed()
        )).perform(click());
    }


    public void checkAllNewsTitleIsDisplayed() {
        Allure.step("Проверка развернутого списка новостей");
        onView(allOf(
                withId(R.id.all_news_text_view),
                withText("ВСЕ НОВОСТИ"),
                isDisplayed()
        )).check(matches(withText("ВСЕ НОВОСТИ")));
    }


    public void butterflyButton() {
        Allure.step("Нажатие на кнопку Бабочка");
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }


    public void buttonAllNews() {
        Allure.step("Нажатие на кнопку ВСЕ НОВОСТИ");
        onView(withId(R.id.all_news_text_view)).perform(click());
    }


    public void openMainMenu() {
        Allure.step("Нажать кнопку Главное меню");
        onView(withId(R.id.main_menu_image_button)).perform(ViewActions.click());
    }


}
