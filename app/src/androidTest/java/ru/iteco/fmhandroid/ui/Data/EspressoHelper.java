package ru.iteco.fmhandroid.ui.Data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

public class EspressoHelper {
    public static void waitForView(int viewId) {
        onView(isRoot()).perform(DataHelper.waitDisplayed(viewId, 5000));
    }

    public static void waitForView(int viewId, long millis) {
        onView(isRoot()).perform(DataHelper.waitDisplayed(viewId, millis));
    }

    public static ViewInteraction onViewWithId(int viewId) {
        return onView(ViewMatchers.withId(viewId));
    }

    public static ViewInteraction onViewWithText(String text) {
        return onView(withText(text));
    }

    public static void checkToastMessage(View decorView, String expectedMessage) {
        onView(withText(expectedMessage))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
