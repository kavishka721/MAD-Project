package com.example.madproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //ConfPC confPC;

    @Before
    public void setUp(){
        //confPC = new ConfPC();
    }

    @Test
    public void addition_isCorrect() {
        int total = ConfPC.getTotal();
        assertEquals(0, total);
    }
}