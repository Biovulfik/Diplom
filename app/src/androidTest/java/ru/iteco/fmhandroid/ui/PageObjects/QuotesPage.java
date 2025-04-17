package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class QuotesPage {


    public void checkLoadingPageQuotes() {
        Allure.step("Проверка загрузки страницы с цитатами");
        onView(withId(R.id.our_mission_title_text_view))
                .check(matches(allOf(
                        isDisplayed(),
                        withText("Главное - жить любя")
                )));
    }


    public void expandQuote(String quoteTitle) {
        Allure.step("Нажать кнопку развернуть цитатау");
        onView(allOf(
                withId(R.id.our_mission_item_title_text_view),
                withText(containsString(quoteTitle))
        )).perform(click());
    }


    public void checkQuoteDescriptionVisible(String descriptionPart) {
        Allure.step("Проверка видимости описания цитаты");
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                isDisplayed()
        )).check(matches(allOf(
                isDisplayed(),
                withText(containsString(descriptionPart))
        )));
    }
}
