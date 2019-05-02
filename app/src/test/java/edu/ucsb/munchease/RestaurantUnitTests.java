package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;

import edu.ucsb.munchease.data.Restaurant;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RestaurantUnitTests {

    Restaurant restaurant1;

    @Before
    public void setUp() {
        restaurant1 = new Restaurant("Restaurant 1", "5", 10, "$$$$", "1000 Epic Street");
    }

    @Test
    public void getName_isCorrect_1() {
        assertEquals("Restaurant 1", restaurant1.getName());
    }

    @Test
    public void getRating_isCorrect_1() {
        assertEquals("5", restaurant1.getRating());
    }

    @Test
    public void getNumberOfReviews_isCorrect_1() {
        assertEquals(10, restaurant1.getNumberOfReviews());
    }

    @Test
    public void getPrice_isCorrect_1() {
        assertEquals("$$$$", restaurant1.getPrice());
    }

    @Test
    public void getAddress_isCorrect_1() {
        assertEquals("1000 Epic Street", restaurant1.getAddress());
    }

    @Test
    public void getVotes_isCorrect_1() {
        assertEquals(0, restaurant1.getVotes());
    }

    @Test
    public void upvote_isCorrect_1() {
        restaurant1.upvote();
        assertEquals(1, restaurant1.getVotes());
    }

    @Test
    public void downvote_isCorrect_1() {
        restaurant1.upvote();
        restaurant1.downvote();
        assertEquals(0, restaurant1.getVotes());
    }
}