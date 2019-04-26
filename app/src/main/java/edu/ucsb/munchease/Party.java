package edu.ucsb.munchease;

import java.util.Vector;

public class Party {
    Vector<Restaurant> restaurants;
    int members;

    public Party() {
        restaurants = new Vector<Restaurant>();
        members = 0;
    }

    public void addMember() {
        members++;
    }

    public void addResturant(Restaurant r) {
        restaurants.add(r);
    }

    public Vector<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getMembers() {
        return members;
    }
}
