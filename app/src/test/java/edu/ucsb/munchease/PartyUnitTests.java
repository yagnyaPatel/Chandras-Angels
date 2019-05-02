package edu.ucsb.munchease;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.data.Restaurant;

import static org.junit.Assert.assertEquals;

public class PartyUnitTests {

    private Party party1;
    private Party party2;
    private Restaurant restaurant1;

    @Before
    public void setUp() {
        restaurant1 = new Restaurant("Restaurant 1", "5", 25, "$$", "1234 The Street");

        party1 = new Party();

        party2 = new Party();
        party2.addRestaurant(restaurant1);
    }


    @Test
    public void getPartyID_isCorrect_1() {
        assertEquals("123456", party1.getPartyID());
    }

    @Test
    public void getMembers_isCorrect_1() {
        assertEquals(1, party1.getMembers());
    }

    @Test
    public void getRestaurants_isCorrect_1() {
        assertEquals(restaurant1, party2.getRestaurants().get(0));
    }

    @Test
    public void getRestaurants_isCorrect_2() {
        ArrayList<Restaurant> list = new ArrayList<>();
        list.add(restaurant1);

        assertEquals(list, party2.getRestaurants());
    }
}
