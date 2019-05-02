package edu.ucsb.munchease.data;

import java.util.ArrayList;

public class Party {
    private ArrayList<Restaurant> restaurants;
    private int members;
    private String partyID;

    public Party() {
        restaurants = new ArrayList<>();
        members = 1;
        partyID = "123456";
    }

    public Party(ArrayList<Restaurant> restaurants, int members, String partyID) {
        this.restaurants = restaurants;
        this.members = members;
        this.partyID = partyID;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getMembers() {
        return members;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
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

    public void clearRestaurants() {
        restaurants.clear();
    }
}
