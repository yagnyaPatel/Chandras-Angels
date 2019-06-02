package edu.ucsb.munchease.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Party {
    private ArrayList<Restaurant> restaurants;
    private int members;
    private String partyID;

    /**
     * Default Constructor - Sets restaurants to empty, members to 1, and partyID to the default of 123456
     */
    public Party() {
        restaurants = new ArrayList<>();
        members = 1;
        partyID = "123456";
    }

    public Party(String partyID) {
        this.partyID = partyID;
        restaurants = new ArrayList<>();
        members = 1;
    }

    /**
     * Creates a new party using the specified parameters
     * @param restaurants The ArrayList of Restaurants that are available to be voted on
     * @param members The number of members in the party
     * @param partyID The ID of the party that can be shared to join, and is also used in Firebase to identify the party
     */
    public Party(ArrayList<Restaurant> restaurants, int members, String partyID) {
        this.restaurants = restaurants;
        this.members = members;
        this.partyID = partyID;
    }

    /**
     * Returns the ArrayList of Restaurants to be voted on
     * @return The Restaurants
     */
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * Returns the number of members in the party
     * @return The number of members in the party
     */
    public int getMembers() {
        return members;
    }

    /**
     * Returns the ID of the party
     * @return The ID of the party
     */
    public String getPartyID() {
        return partyID;
    }

    /**
     * Sets the ArrayList of Restaurants to the new list which is passed
     * @param restaurants The new list of Restaurants
     */
    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Sets the number of members in the party to the passed parameter
     * @param members The new number of members in the party
     */
    public void setMembers(int members) {
        this.members = members;
    }

    /**
     * Increments the value of 'members' by one
     */
    public void addMember() {
        members++;
    }

    /**
     * Adds the passed Restaurant to the list of Restaurants
     * @param r The restaurant to add to the ArrayList
     */
    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    /**
     * Clears the list of Restaurants so it is empty
     */
    public void clearRestaurants() {
        restaurants.clear();
    }

    public void sortRestaurants() {
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o2.getVotes() - o1.getVotes();
            }
        });
    }
}
