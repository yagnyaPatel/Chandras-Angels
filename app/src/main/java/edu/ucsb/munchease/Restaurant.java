package edu.ucsb.munchease;

public class Restaurant {
    private String name;
    private int rating;
    private String address;
    private int votes;

    public Restaurant() {
    }

    public Restaurant(String name, int rating, String address, int votes) {
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public int getVotes() {
        return votes;
    }
}
