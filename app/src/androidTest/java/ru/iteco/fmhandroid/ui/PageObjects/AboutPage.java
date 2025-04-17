package ru.iteco.fmhandroid.ui.PageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.action.ViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {

    public void clickPrivacyPolicyLink() {
        Allure.step("Нажать на ссылку Политика конфиденциальности");
        onView(allOf(
                withId(R.id.about_privacy_policy_value_text_view)))
                .perform(ViewActions.click());
    }


    public void clickTermsOfUseLink() {
        Allure.step("Нажать на ссылку Пользовательское соглашение");
        onView(allOf(
                withId(R.id.about_terms_of_use_value_text_view)))
                .perform(ViewActions.click());
    }
}
