package com.arksana.filijet;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
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
import static org.hamcrest.core.AllOf.allOf;


public class TestMainActivityAndDetailActivity {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cekToDetail() {
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.btn_detail)));
        onView(withId(R.id.tv_judul)).check(matches(withText("Joker")));
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(withText("Terminator: Dark Fate")));
        onView(withId(R.id.btn_prev)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(withText("Joker")));
    }

    @Test
    public void cekToolbar() {
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

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

}