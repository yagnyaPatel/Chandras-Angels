package edu.ucsb.munchease.data;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

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

    /**
     * Returns a JSON list of restaurants returned by the provided search term
     * within a 4000 meter radius of San Raf.
     * @param searchTerm
     * @return a JSON string containing restaurants
     */
    public static String yelpRadiusSearch(String searchTerm) {
        Map<String, String> params = new HashMap<>();
        params.put("term", "restaurants");
        params.put("term", searchTerm);
        params.put("latitude", "34.411501");
        params.put("longitude", "-119.853554");
        params.put("radius", "2000");

        // send request
        Call<SearchResponse> call = yelpApi.getBusinessSearch(params);
        try {
            Response<SearchResponse> response = call.execute();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Restaurant> getRestaurantsFromJsonArray(String json) {
        ArrayList<Restaurant> returnList = new ArrayList<Restaurant>();

        // Parse JSON response
        JsonElement element = new JsonParser().parse(json);
        JsonObject obj = element.getAsJsonObject();
        // Iterate through businesses array
        JsonArray businesses = obj.get("businesses").getAsJsonArray();
        for(JsonElement restaurantElement : businesses) {
            String restaurantJson = element.getAsString();
            try {
                Restaurant r = RestaurantParser.parseRestaurantFromYelpResponse(restaurantJson);
                returnList.add(r);
            } catch(InvalidJsonException e) {
                // Do nothing
            }
        }
        // STUB
        return returnList;
    }
}
