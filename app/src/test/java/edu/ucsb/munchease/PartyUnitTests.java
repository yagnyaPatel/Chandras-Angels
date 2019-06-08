package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.RestaurantParser;
import edu.ucsb.munchease.data.InvalidJsonException;

import static org.junit.Assert.assertEquals;

public class PartyUnitTests {

    private Party party1;
    private Party party2;
    private Restaurant restaurant1;
    private Restaurant restaurant2;

    @Before
    public void setUp() {
        try {
            restaurant1 = RestaurantParser.parseRestaurantFromYelpResponse(SampleJsonRestaurants.completeExample);
            restaurant2 = RestaurantParser.parseRestaurantFromYelpResponse(SampleJsonRestaurants.completeExample);
        } catch(InvalidJsonException e) {
            restaurant1 = null;
            restaurant2 = null;
        }

        party1 = new Party();

        ArrayList<Restaurant> list = new ArrayList<>();
        list.add(restaurant1);
        party2 = new Party(list, 10, "555555");

    }
//    Constructor
    @Test
    public void party_isCorrect_1() {
        assertEquals("555555", party2.getPartyID());
    }

    @Test
    public void party_isCorrect_2() {
        assertEquals(10, party2.getMembers());
    }

    @Test
    public void party_isCorrect_3() {
        ArrayList<Restaurant> list = new ArrayList<>();
        list.add(restaurant1);
        assertEquals(list, party2.getRestaurants());
    }

//    Getters
    @Test
    public void getPartyID_isCorrect_1() {
        assertEquals("123456", party1.getPartyID());
    }

    @Test
    public void getMembers_isCorrect_1() {
        assertEquals(1, party1.getMembers());
    }

//    @Test
//    public void getRestaurants_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }

//    @Test
//    public void getRestaurants_isCorrect_2() {
//        ArrayList<Restaurant> list = new ArrayList<>();
//        list.add(restaurant1);
//
//        assertEquals(list, party2.getRestaurants());
//    }

//    Mutators
//    @Test
//    public void setRestaurants_isCorrect_1() {
//        ArrayList<Restaurant> list = new ArrayList<>();
//        list.add(restaurant1);
//
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }

//    @Test
//    public void setMembers_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }

//    @Test
//    public void addMember_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }
//
//    @Test
//    public void addRestaurant_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }
//
//    @Test
//    public void clearRestaurants_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }
//
//    @Test
//    public void sortRestaurants_isCorrect_1() {
//        assertEquals(restaurant1, party2.getRestaurants().get(0));
//    }
}
