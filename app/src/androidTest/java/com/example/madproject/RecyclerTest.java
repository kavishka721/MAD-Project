package com.example.madproject;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;

public class RecyclerTest {

    @Rule
    public IntentsTestRule<viewparts> adminIntentsTestRule = new IntentsTestRule<viewparts>(viewparts.class);

    @Test
    public void testrecycle(){
        //onView(withId(R.id.partRecycleView))
                //.perform(RecyclerViewActions.scrollToPosition(2));
               // .check(matches(atPosition(0, hasDescendant(withText("Test Text")))));
    }

}
