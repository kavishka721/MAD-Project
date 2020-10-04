package com.example.madproject;

import android.view.View;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.junit.Assert.assertNotNull;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class TestIntent {

    private savedConfigNew savedConfigNew = null;

    @Rule
    public IntentsTestRule<PCConf> pcConfIntentsTestRule = new IntentsTestRule<PCConf>(PCConf.class);

    @Test
    public void testConfIntent(){
        onView(withId(R.id.button13)).perform(click());
        intended(hasComponent(ConfPC.class.getName()));
    }

    @Test
    public void testBuildIntent() {
        onView(withId(R.id.button20)).perform(click());
        intended(hasComponent(savedConfigNew.class.getName()));
    }

    /*@After
    public void TearDown(){
        savedConfigNew = null;
    }*/
}
