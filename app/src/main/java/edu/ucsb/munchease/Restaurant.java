package edu.ucsb.munchease;

import com.google.gson.*;

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

    private RestaurantSchedule schedule; // Contains all time and schedule related info

    private int votes;

    /**
     *Default Constructor - Leaves everything empty
     * TODO may not need
     */
    // public Restaurant() { }

    /**
     * Initializes most data members to the passed parameters
     * @param name The name of the restaurant
     * @param rating The rating of the restaurant, from 1 to 5 in increments of 0.5
     * @param reviewCount The number of Yelp reviews for the restaurant
     * @param price The price of the restaurant, from  $ to $$$$
     * @param address The street address of the restaurant
     */

    // TODO delete this if necessary

    /** Old Constructor
    public Restaurant(String name, String rating, int reviewCount, String price, String address) {
        this.name = name;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.price = price;
        this.address = address;
        this.votes = 0;
    }
    */

    // TODO delete this
    // Example with only things we care about


    /**
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


    public Restaurant(String jsonInput) {
        // Get entire object
        JsonElement element = new JsonParser().parse(jsonInput);
        JsonObject response = element.getAsJsonObject();

        alias = response.get("alias").getAsString();
        name = response.get("name").getAsString();
        url = response.get("url").getAsString();
        rating = response.get("rating").getAsString();
        reviewCount = response.get("review_count").getAsInt();
        price = response.get("price").getAsString();

        // Get display address from location object
        JsonObject location = response.get("location").getAsJsonObject();
        JsonArray addrArray = location.get("display_address").getAsJsonArray();
        // Iterate through array to concatenatae to string
        address = new String();
        for(int i = 0; i < addrArray.size(); i++) {
            address += addrArray.get(i).getAsString();
            // Add newline to all lines except last
            if(i < addrArray.size() - 1)
                address += "\n";
        }

        // Get latitude and longitude from coordinates array
        JsonObject coordinates = response.get("coordinates").getAsJsonObject();
        latitude = coordinates.get("latitude").getAsDouble();
        longitude = coordinates.get("longitude").getAsDouble();

        JsonArray hours = response.get("hours").getAsJsonArray();
        schedule = new RestaurantSchedule(hours);
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
     * Returns the Restaurant Schedule object
     * @return The Restaurant Schedule object
     */
    public RestaurantSchedule getRestaurantSchedule() {
        return schedule;
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


    //
}