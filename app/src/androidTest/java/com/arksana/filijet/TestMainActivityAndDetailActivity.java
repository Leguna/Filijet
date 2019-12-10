package com.arksana.filijet;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.arksana.filijet.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;


public class TestMainActivityAndDetailActivity {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cekToDetail() {
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.btn_detail)));

        onView(withId(R.id.tv_judul)).check(matches(not(withText(""))));
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(not(withText(""))));
        onView(withId(R.id.btn_prev)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(not(withText(""))));

        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.action_favorite)).perform(click());
    }

    @Test
    public void cekToolbar() {
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Exit")).check(matches(isDisplayed()));
        onView(withText("Exit")).perform(click());
        onView(withText("No")).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Refresh")).perform(click());
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("About Me")).perform(click());
        Espresso.pressBack();
    }

    @Test
    public void cekPagination() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.rv_category);
        assertEquals(20,recyclerView.getAdapter().getItemCount());
        int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()-1;
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.scrollToPosition(itemCount));
        assertEquals(40,recyclerView.getAdapter().getItemCount());
        itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()-1;
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.scrollToPosition(itemCount));
        assertEquals(60,recyclerView.getAdapter().getItemCount());
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.view_pager)).perform(swipeLeft());
    }

    @Test
    public void cekFavorite(){
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.btn_detail)));

        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());

        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.action_favorite)).perform(click());

        Espresso.pressBack();
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(allOf(isDisplayed(), withId(R.id.rv_category)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, MyViewAction.clickChildViewWithId(R.id.btn_detail)));
        onView(withId(R.id.action_favorite)).perform(click());
        pressBack();

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