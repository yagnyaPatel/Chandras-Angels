package edu.ucsb.munchease.data;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;

import java.io.IOException;
import java.util.ArrayList;

// Static class
public class YelpInterface {
    private static YelpFusionApiFactory apiFactory;
    private static YelpFusionApi yelpApi;
    private static String apiKey = "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ-Q04YRy_XAXHYx";

    // Enforce static class
    private YelpInterface() { }

    public static void initialize() {
        // Set up API
        apiFactory = new YelpFusionApiFactory();
        try {
            yelpApi = apiFactory.createAPI(apiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String yelpRadiusSearch() {

    }

    public static ArrayList<Restaurant> getRestaurantsFromJsonArray(String json) {

    }
}
