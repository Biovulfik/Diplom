package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.Data.DataHelper.withIndex;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.EspressoHelper;

public class NewsPage {

    public void buttonControlPanel() {
        Allure.step("Нажать кнопку панель управления");
        onView(withId(R.id.edit_news_material_button))
                .perform(click());
    }


    public void ButtonAddNews() {
        Allure.step("Нажать кнопку добавить новость");
        onView(withId(R.id.add_news_image_view))
                .perform(click());
    }


    public void sortNews() {
        Allure.step("Нажать кнопку сортировки");
        onView(withId(R.id.sort_news_material_button))
                .perform(click());
    }


    public void verifyNewsExists(String title) {
        Allure.step("Проверка появления новости");
        onView(withIndex(withId(R.id.news_item_title_text_view), 0))
                .check(matches(withText(title)));
    }


    public void waitUntilLoaded() {
        Allure.step("Ожидание загрузки страницы новостей");
        EspressoHelper.waitForView(R.id.edit_news_material_button);
    }
}
