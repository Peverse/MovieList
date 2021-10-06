package com.example.movielist;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void createMovie(){
        Movie movie = new Movie();
        movie.Title = "test";
        movie.Genre = "test";
        movie.Year = "test";
        assertEquals(movie.Title, "test");
        assertEquals(movie.Genre, "test");
        assertEquals(movie.Year, "test");
    }
}