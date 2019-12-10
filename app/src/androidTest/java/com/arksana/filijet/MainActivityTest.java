package com.arksana.filijet;

import android.widget.Adapter;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.arksana.filijet.adapter.CardViewFilmAdapter;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cekToDetail() {
        onView(allOf(isDisplayed(), withId(R.id.rv_category))).check(matches(isDisplayed()));
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.btn_detail)));
        onView(withId(R.id.tv_judul)).check(matches(withText("Girl Under the Ketapang Tree")));

        Espresso.pressBack();
    }

    @Test
    public void cekToolbar() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("View")).check(matches(isDisplayed()));
        onView(withText("View")).perform(click());
        onView(withText("Grid")).check(matches(isDisplayed()));
        onView(withText("Grid")).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("View")).check(matches(isDisplayed()));
        onView(withText("View")).perform(click());
        onView(withText("List")).check(matches(isDisplayed()));
        onView(withText("List")).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("View")).check(matches(isDisplayed()));
        onView(withText("View")).perform(click());
        onView(withText("Card")).check(matches(isDisplayed()));
        onView(withText("Card")).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Exit")).check(matches(isDisplayed()));
        onView(withText("Exit")).perform(click());
        onView(withText("No")).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Refresh")).perform(click());
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("About Me")).perform(click());
        Espresso.pressBack();
    }


}