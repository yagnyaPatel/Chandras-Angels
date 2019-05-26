package edu.ucsb.munchease.data;

public class Restaurant {
    private String alias;
    private String name;
    private String url;

    private String rating;
    private int reviewCount;
    private String price;

    private String address;

    private double latitude;
    private double longitude;

//    private RestaurantSchedule schedule; // Contains all time and schedule related info

    private int votes;

    public Restaurant() {

    }

    /**
     * Initializes most data members to the passed parameters
     * @param name The name of the restaurant
     * @param rating The rating of the restaurant, from 1 to 5 in increments of 0.5
     * @param reviewCount The number of Yelp reviews for the restaurant
     * @param price The price of the restaurant, from  $ to $$$$
     * @param address The street address of the restaurant
     */
    public Restaurant(String alias, String name, String url, String rating,
                      int reviewCount, String price, String address, double latitude,
                      double longitude, RestaurantSchedule schedule, int votes) {

        this.alias = alias;
        this.name = name;
        this.url = url;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.price = price;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.schedule = schedule;
        this.votes = votes;
    }

    // TODO delete this
    // Example with only things we care about


    /**

*/

    /**
     * Returns the unique Yelp alias of the restaurant
     * @return the Yelp alias of the restaurant
     */
    public String getAlias() { return alias; }

    /**
     * Returns the name of the restaurant
     * @return The name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the restaurant URL
     * @return The restaurant URL
     */
    public String getUrl() { return url; }

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
    public int getReviewCount() {
        return reviewCount;
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
     * Returns the GPS latitude of the restaurant
     * @return the GPS latitude of the restaurant
     */
    public double getLatitude() { return latitude; }

    /**
     * Returns the GPS longitude of the restaurant
     * @return the GPS longitude of the restaurant
     */
    public double getLongitude() { return longitude; }

    /**
     * Returns the Restaurant Schedule object
     * @return The Restaurant Schedule object
     */
//    public RestaurantSchedule getSchedule() {
//        return schedule;
//    }

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
