package edu.ucsb.munchease.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.YelpInterface;
import edu.ucsb.munchease.view.SearchAdapter;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Restaurant> restaurants;

    private SearchView searchView;

    private RecyclerView recyclerView_searchSuggestions;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeComponents();
        setUpSearchbar();
        setUpRestaurantList();
    }

    private void initializeComponents() {
        restaurants = new ArrayList<>();

        searchView = findViewById(R.id.searchView);
    }

    /**
     * Sets up the RecyclerView to show the list of restaurants
     */
    private void setUpRestaurantList() {
        recyclerView_searchSuggestions = findViewById(R.id.recyclerView_searchSuggestions);
        recyclerView_searchSuggestions.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView_searchSuggestions.setLayoutManager(layoutManager);

        String partyID = "123-456";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            partyID = extras.getString("partyID");
        }

        mAdapter = new SearchAdapter(restaurants, partyID);
        recyclerView_searchSuggestions.setAdapter(mAdapter);
    }

    private void setUpSearchbar() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sendYelpRequest(query);
                Log.d("array", "restaurants.size() = " + restaurants.size());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sendYelpRequest(newText);
                return false;
            }
        });
    }

    /**
     * Sends a Yelp API request with the term passed as a parameter
     * @param searchTerm The term to pass as the Yelp search term
     */
    private void sendYelpRequest(String searchTerm) {
        // Create request queue and perform search with no parameters
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = YelpInterface.yelpRadiusURL(searchTerm);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // Add response restaurants to party
                        restaurants.clear();

                        ArrayList<Restaurant> localRestaurants = YelpInterface.getRestaurantsFromJsonArray(response);

                        for(Restaurant r : localRestaurants) {
                            restaurants.add(r);
                        }

                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        }) {
            // Set Yelp authorization header
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + YelpInterface.getApiKey());
                return headers;
            }
        };
        // Add request to queue
        queue.add(stringRequest);
    }
}
