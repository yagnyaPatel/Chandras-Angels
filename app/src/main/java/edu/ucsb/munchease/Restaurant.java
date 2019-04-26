package edu.ucsb.munchease;

public class Restaurant {
    private String name;
    private double rating;
    private String address;
    private int votes;

    public Restaurant() {
    }

    public Restaurant(String name, double rating, String address) {
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public int getVotes() {
        return votes;
    }
}
