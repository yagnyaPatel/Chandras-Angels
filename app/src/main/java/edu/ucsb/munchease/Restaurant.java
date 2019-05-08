package edu.ucsb.munchease;

import com.google.gson.*;

public class Restaurant {
    private String name;

    private String rating;
    private int reviewCount;
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
     * @param reviewCount The number of Yelp reviews for the restaurant
     * @param price The price of the restaurant, from  $ to $$$$
     * @param address The street address of the restaurant
     */
    public Restaurant(String name, String rating, int reviewCount, String price, String address) {
        this.name = name;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.price = price;
        this.address = address;
        this.votes = 0;
    }

    // TODO delete this
    // Example with only things we care about
    /*
    {
  "alias": "gary-danko-san-francisco",
  "name": "Gary Danko",
  "url": "httk1CeT8POg",
  "review_count": 5296,
  "categories": [
    {
      "alias": "newamerican",
      "title": "American (New)"
    } // and others
  ],
  "rating": 4.5,
  "location": {
    "display_address": [
      "800 N Point St",
      "San Francisco, CA 94109"
    ],
  },
  "coordinates": {
    "latitude": 37.80587,
    "longitude": -122.42058
  },
  "price": "$$$$",
  "hours": [
    {
      "open": [
        {
          "is_overnight": false,
          "start": "1730",
          "end": "2200",
          "day": 0
        } // and so on
      ],
      "is_open_now": false
    }
  ]
}
*/


    public static Restaurant parseYelpData(String jsonInput) {
        // Declare variables
        String alias;
        String name;
        String url;
        int reviewCount; // review_count, numberOfReviews

        // Get entire object
        JsonElement element = new JsonParser().parse(jsonInput);
        JsonObject object = element.getAsJsonObject();

        // Get
        // TODO stub
        return new Restaurant();
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