package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.action.ViewActions;

import io.qameta.allure.kotlin.Allure;

public class MenuPage {


    public void selectAbout() {
        Allure.step("Нажать кнопку О приложении");
        onView(allOf(
                withId(android.R.id.title),
                withText("О приложении")))
                .perform(ViewActions.click());
    }
}
