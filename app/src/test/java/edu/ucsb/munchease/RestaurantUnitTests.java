package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;

import edu.ucsb.munchease.data.InvalidJsonException;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.RestaurantParser;

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
        try {
            restaurant1 = RestaurantParser.parseRestaurantFromYelpResponse(SampleJsonRestaurants.completeExample);
        } catch(InvalidJsonException e) {
            restaurant1 = null;
        }
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