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

    public Party(Vector<Restaurant> restaurants, int members, String partyID) {
        this.restaurants = restaurants;
        this.members = members;
        this.partyID = partyID;
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

    public void setRestaurants(Vector<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public void addMember() {
        members++;
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }
}
