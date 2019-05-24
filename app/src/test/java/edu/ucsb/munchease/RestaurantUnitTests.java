package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;

import edu.ucsb.munchease.data.InvalidJsonException;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.RestaurantParser;
import edu.ucsb.munchease.data.RestaurantSchedule;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RestaurantUnitTests {

    Restaurant restaurant1;
    RestaurantSchedule schedule;

    @Before
    public void setUp() {
        try {
            restaurant1 = RestaurantParser.parseRestaurantFromYelpResponse(SampleJsonRestaurants.completeExample);
        } catch(InvalidJsonException e) {
            restaurant1 = null;
        }
    }

//    Getter Tests
    @Test
    public void getAlias_isCorrect_1() {
        assertEquals("gary-danko-san-francisco", restaurant1.getAlias());
    }

    @Test
    public void getName_isCorrect_1() {
        assertEquals("Gary Danko", restaurant1.getName());
    }

    @Test
    public void getUrl_isCorrect_1() {
        assertEquals("https://www.yelp.com/biz/gary-danko-san-francisco?adjust_creative=wpr6gw4FnptTrk1CeT8POg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=wpr6gw4FnptTrk1CeT8POg", restaurant1.getUrl());
    }

    @Test
    public void getRating_isCorrect_1() {
        assertEquals("4.5", restaurant1.getRating());
    }

    @Test
    public void getReviewCount_isCorrect_1() {
        assertEquals(5296, restaurant1.getReviewCount());
    }

    @Test
    public void getPrice_isCorrect_1() {
        assertEquals("$$$$", restaurant1.getPrice());
    }

    @Test
    public void getAddress_isCorrect_1() {
        assertEquals("800 N Point St\n" +
                "San Francisco, CA 94109", restaurant1.getAddress());
    }

//    @Test
//    public void getLatitude_isCorrect_1() {
//        assertEquals(37.80587 , restaurant1.getLatitude());
//    }
//
//    @Test
//    public void getLongitude_isCorrect_1() {
//        assertEquals(-122.42058 , restaurant1.getLongitude());
//    }
//
//    @Test
//    public void getSchedule_isCorrect_1() {
//        assertEquals(0 , restaurant1.getSchedule());
//    }

    @Test
    public void getVotes_isCorrect_1() {
        assertEquals(0, restaurant1.getVotes());
    }



    //    Voting Tests
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