package edu.ucsb.munchease.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;

import java.io.IOException;
import java.util.ArrayList;

//import retrofit2.Call;
//import retrofit2.Response;

public class YelpInterface {
    private YelpFusionApiFactory apiFactory;
    private YelpFusionApi yelpApi;
    private static final String apiKey = "_OQqAnq91MYWUPjoqbXMTIcDSpcoIcXqhbKDASfG1CQf1OXmyL7Zjf1DHPwwncAk4sOuc1YQY79xcynpQ93ewSUMfynihNmTR1ckaAWNNeNhfIVLhQ-Q04YRy_XAXHYx";

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

    public static String getApiKey() { return apiKey; }

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

    // Get URL with parameters
    public static String yelpRadiusURL(String searchTerm) {
        // Initialize url
        String url = "https://api.yelp.com/v3/businesses/search";

        // Add URL parameters
        url += "?term=restaurants+";

        if(searchTerm != null && searchTerm.length() > 0) {
            url += (searchTerm);
        }

        url += "&latitude=34.411501";
        url += "&longitude=-119.853554";
        url += "&radius=1000"; // TODO Change value

        // TODO
        return url;

        /*// Set up request queue
        RequestQueue requestQueue = Volley.newRequestQueue(volleyContext);
        // Create synchronous request, add to queue
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
        requestQueue.add(request);

        // Try actually getting the request
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
        return null;*/
    }

    public static ArrayList<Restaurant> getRestaurantsFromJsonArray(String json) {
        ArrayList<Restaurant> returnList = new ArrayList<Restaurant>();

        // Parse JSON response
        JsonElement element = new JsonParser().parse(json);
        JsonObject obj = element.getAsJsonObject();
        // Iterate through businesses array
        JsonArray businesses = obj.get("businesses").getAsJsonArray();
        for(JsonElement restaurantElement : businesses) {
            String restaurantJson = restaurantElement.toString();
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
