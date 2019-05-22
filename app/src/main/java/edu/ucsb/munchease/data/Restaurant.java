package edu.ucsb.munchease.data;

public class Restaurant {
    private String name;

    private String rating;
    private int numberOfReviews;
    private String price;

    private String address;

    private int votes;

    /**
     *Default Constructor - Leaves everything empty
     */
    public Restaurant() {
    }

    /**
     * Initializes most data members to the passed parameters
     * @param name The name of the restaurant
     * @param rating The rating of the restaurant, from 1 to 5 in increments of 0.5
     * @param numberOfReviews The number of Yelp reviews for the restaurant
     * @param price The price of the restaurant, from  $ to $$$$
     * @param address The street address of the restaurant
     */
    public Restaurant(String name, String rating, int numberOfReviews, String price, String address) {
        this.name = name;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.price = price;
        this.address = address;
        this.votes = 0;
    }

    /**
     * Returns the name of the restaurant
     * @return The name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rating of the restaurant, from 1.0 to 5.0 in increments of 0.5
     * @return The rating of the restaurant
     */
    public String getRating() {
        return rating;
    }

    /**
     * Returns the number of Yelp reviews the restaurant has
     * @return The number of Yelp reviews the restaurant has
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * Returns the price of the restaurant, from $ to $$$$
     * @return The price of the restaurant, according to Yelp
     */
    public String getPrice() {
        return price;
    }

    /**
     * Returns the street address of the restaurant
     * @return The street address of the restaurant
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the number of votes the restaurant has in the current party
     * @return The number of votes the restaurant has in the current party
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Increments the value of 'votes' by one
     */
    public void upvote() {
        votes++;
    }

    /**
     * Decrements the value of 'votes' by one
     */
    public void downvote() {
        votes--;
    }
}
