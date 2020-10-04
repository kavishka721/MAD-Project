package com.example.madproject;

import android.view.View;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.ViewAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;

public class IntentTesting {

//    @Rule
//    public IntentsTestRule<AdminPage> adminPageIntentsTestRule = new IntentsTestRule<>(AdminPage.class);
//
//    public void testAdminIntent(){
//        onView(withId(R.id.btn_adminAddPart)).perform(click());
//        intended(hasComponent(addPart.class.getName()));
//    }

    @Rule
    public IntentsTestRule<Home> adminIntentsTestRule = new IntentsTestRule<Home>(Home.class);

    @Test
    public void testHomeIntent(){
        onView(withId(R.id.button3)).perform(click());
        intended(hasComponent(viewparts.class.getName()));
    }



}
