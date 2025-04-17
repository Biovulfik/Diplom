package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.Data.DataHelper.withIndex;

import android.view.View;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Data.EspressoHelper;

public class NewsCreationPage {

    private static final String CATEGORY_ANNOUNCEMENT = "Объявление";
    private static final String CATEGORY_BIRTHDAY = "День рождения";

    public String getAnnouncement() {
        return CATEGORY_ANNOUNCEMENT;
    }

    public String getBirthday() {
        return CATEGORY_BIRTHDAY;
    }


    public void selectCategory(String category) {
        Allure.step("Выбор категории");
        onView(allOf(
                withId(R.id.text_input_end_icon),
                isDescendantOfA(withId(R.id.news_item_category_text_input_layout))
        )).perform(click());

        onView(withText(category))
                .inRoot(isPlatformPopup())
                .perform(click());
    }


    public void enterCategoryManually(String category) {
        Allure.step("Ввод категории вручную");
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click(), replaceText(category), closeSoftKeyboard());
    }


    public void newsHeadline(String title) {
        Allure.step("Ввод заголовка новости");
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText(title), closeSoftKeyboard());
    }


    public void setCurrentDate() {
        Allure.step("Выбор даты");
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
    }


    public void setCurrentTime() {
        Allure.step("Выбор времени");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
    }


    public void setTimeManually(int hours, int minutes) {
        Allure.step("Ввод времени вручную");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        onView(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")))
                .perform(click());
        onView(withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 0))
                .perform(replaceText(String.valueOf(hours)));
        onView(withIndex(withClassName(is("androidx.appcompat.widget.AppCompatEditText")), 1))
                .perform(replaceText(String.format("%02d", minutes)));
        onView(withId(android.R.id.button1))
                .perform(click());
    }


    public void enterDescription(String description) {
        Allure.step("Ввод описания");
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(description), closeSoftKeyboard());
    }


    public void saveNews() {
        Allure.step("Сохранение новости");
        onView(withId(R.id.save_button))
                .perform(click());
    }


    public void verifyErrorMessage(String message, View decorView) {
        Allure.step("Проверка сообщения об ошибке");
        EspressoHelper.checkToastMessage(decorView, message);
    }
}

