package edu.ucsb.munchease.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import com.google.gson.*;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

//import retrofit2.Call;
//import retrofit2.Response;

public class YelpInterface {
    private YelpFusionApiFactory apiFactory;
    private YelpFusionApi yelpApi;
    private static String apiKey = "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ-Q04YRy_XAXHYx";

    // Enforce static class
    public YelpInterface() {
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
    /*
    public String yelpRadiusSearch(String searchTerm) {
        Map<String, String> params = new HashMap<>();
        // Search for restaurants with search term
        params.put("term", "restaurants");
        if(searchTerm != null) {
            params.put("term", searchTerm);
        }
        // Search within 2,000 meter radius of San Raf
        params.put("latitude", "34.411501");
        params.put("longitude", "-119.853554");
        params.put("radius", "2000");

        // Send request
        Call<SearchResponse> call = yelpApi.getBusinessSearch(params); // TODO Crashes here
        try {
            Response<SearchResponse> response = call.execute();
            //String responseStr = response.toString(); // TODO get rid of this
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public String yelpRadiusSearch(String searchTerm) {
        // Initialize url
        String url = "https://api.yelp.com/v3/businesses/search";

        // Add URL parameters
        url += "?term=restaurants";

        if(searchTerm != null) {
            url += ("&term=" + searchTerm);
        }
        url += "&term=restaurants";
        url += "&latitude=34.411501";
        url += "&longitude=-119.853554";
        url += "&radius=1000"; // TODO Change value

        // Send synchronous request
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(), requestFuture, requestFuture) {
            // Set API authorization header
            @Override
            public HashMap<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + apiKey);
                return params;
            }
        };

        try {
            JSONObject response = requestFuture.get();
            String returnStr = response.toString();
            return returnStr;
        } catch (InterruptedException e) {
            e.printStackTrace(); // cry
        } catch (ExecutionException e) {
            e.printStackTrace(); // cry in a different flavor
        }

        // Return null if the request didnt work
        return null;
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
