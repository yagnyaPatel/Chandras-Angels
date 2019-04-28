package edu.ucsb.munchease.data;

public class Restaurant {
    private String name;

    private String rating;
    private int numberOfReviews;
    private String price;

    private String address;

    private int votes;

    public Restaurant() {
    }


    public Restaurant(String name, String rating, int numberOfReviews, String price, String address) {
        this.name = name;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.price = price;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public int getVotes() {
        return votes;
    }
}
