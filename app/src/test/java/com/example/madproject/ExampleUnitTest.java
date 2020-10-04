package com.example.madproject;

import com.example.madproject.classes.CPUBuild;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {


    CPUBuild cpuBuild;


    @Before
    public void setUp(){
        //confPC = new ConfPC();
        cpuBuild = new CPUBuild();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(0, 0);
    }
}