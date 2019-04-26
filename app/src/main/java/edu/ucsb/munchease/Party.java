package edu.ucsb.munchease;

import java.util.Vector;

public class Party {
    Vector<Restaurant> restaurants;
    int members;
    String partyID;

    public Party() {
        restaurants = new Vector<Restaurant>();
        members = 1;
        partyID = "123456";
    }

    public Vector<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getMembers() {
        return members;
    }

    public String getPartyID() {
        return partyID;
    }

    public void addMember() {
        members++;
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }
}
