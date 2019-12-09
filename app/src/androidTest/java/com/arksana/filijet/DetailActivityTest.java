package com.arksana.filijet;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.arksana.filijet.data.Film;
import com.arksana.filijet.data.FilmData;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailActivityTest {

    private ArrayList<Film> films = FilmData.getListData(1);

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.EXTRA_FILMS, films);
            result.putExtra(DetailActivity.EXTRA_ID, 1);
            return result;
        }
    };

    @Test
    public void pengecekanDetail() {
        onView(withId(R.id.tv_judul)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_judul)).check(matches(withText("Glass")));
        onView(withId(R.id.btn_prev)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_judul)).check(matches(withText("Girl Under the Ketapang Tree")));
        onView(withId(R.id.btn_prev)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(withText("Venom")));
        onView(withId(R.id.btn_next)).perform(click());
        onView(withId(R.id.tv_judul)).check(matches(withText("Girl Under the Ketapang Tree")));
        onView(withId(R.id.btn_link)).perform(scrollTo(), click());
    }

}