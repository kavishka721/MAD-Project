package com.example.madproject;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TestConfigpc {


//    private savedConfigNew savedConfigNew = null;
//
//    @Rule
//    public IntentsTestRule<PCConf> pcConfIntentsTestRule = new IntentsTestRule<PCConf>(PCConf.class);

//    @Test
//    public void testConfIntent(){
//        onView(withId(R.id.button13)).perform(click());
//        intended(hasComponent(ConfPC.class.getName()));
//    }
//
//    @Test
//    public void testBuildIntent() {
//        onView(withId(R.id.button20)).perform(click());
//        intended(hasComponent(savedConfigNew.class.getName()));
//    }

    @Rule
    public IntentsTestRule<Home> adminIntentsTestRule = new IntentsTestRule<Home>(Home.class);

    @Test
    public void testHomeIntent(){
        onView(withId(R.id.partsname_casings)).perform(click());
        intended(hasComponent(viewparts.class.getName()));
    }



}
