package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.data.YelpInterface;
import edu.ucsb.munchease.view.RestaurantAdapter;

// TODO Temporary

public class PartyHomeActivity extends AppCompatActivity {

    //------------------------------------------------------------------
    // *** INSTANCE VARIABLES ***
    //------------------------------------------------------------------

    //The party
    private Party party;

    // Yelp Interface
    private YelpInterface yelpInterface;

    //Visual components of the app
    private TextView textView_homeText;

    private FloatingActionButton button_addRestaurants;
    private FloatingActionButton button_clearRestaurants;
    private FloatingActionButton button_startSearch;

    private RecyclerView recyclerView_restaurantList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore db;
    private DocumentReference partyDocRef;
    private CollectionReference restaurantsRef;

    //------------------------------------------------------------------
    // *** MEMBER FUNCTIONS ***
    //------------------------------------------------------------------

    /**
     * Called on the creation of the activity
     * @param savedInstanceState The previous state of the activity?
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_home);

//        party = new Party();
        initParty();

        //Initialize Yelp api
        yelpInterface = new YelpInterface();

        setUpFirebase();
        setUpRestaurantList();
        setUpRestaurantsListener();

        initComponents();
    }

    /**
     * Assigns all of the visual components of the app
     */
    private void initComponents() {
        textView_homeText = findViewById(R.id.textView_homeText);
        textView_homeText.setText("Party " + party.getPartyID());

        // Does not actually add random restaurant
        button_addRestaurants = findViewById(R.id.button_addRestaurants);
        button_addRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendYelpRequest("food");
            }
        });

        button_clearRestaurants = findViewById(R.id.button_resetRestaurants);
        button_clearRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                party.clearRestaurants();
                partyDocRef.set(party);
                clearRestaurants();
            }
        });

        button_startSearch = findViewById(R.id.button_startSearch);
        button_startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
                goToSearchActivity.putExtra("partyID", party.getPartyID());
                startActivity(goToSearchActivity);
            }
        });
    }

    /**
     * Sets up the RecyclerView to show the list of restaurants
     */
    private void setUpRestaurantList() {
        recyclerView_restaurantList = findViewById(R.id.recyclerView_restaurantList);
        recyclerView_restaurantList.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView_restaurantList.setLayoutManager(layoutManager);

        //Specify an adapter
        mAdapter = new RestaurantAdapter(party.getRestaurants(), party.getPartyID());
        recyclerView_restaurantList.setAdapter(mAdapter);
    }

    /**
     * Initializes the Firebase references
     */
    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
        partyDocRef = db.collection("parties").document(party.getPartyID());
        restaurantsRef = partyDocRef.collection("restaurants");

        partyDocRef.set(party);
    }

    /**
     * Sets up the list so it will listen for any changes in the database
     */
    private void setUpRestaurantsListener() {
        restaurantsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                String TAG = "---LISTENER---";

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (queryDocumentSnapshots != null) {
                    Log.d(TAG, "Got query of restaurants");

                    party.clearRestaurants();

                    for(DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                        party.addRestaurant(d.toObject(Restaurant.class));
                    }

                    party.sortRestaurants();

                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    private void initParty() {
        String sharePartyID = "123-456";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sharePartyID = extras.getString("sharePartyID");
        }

        party = new Party(sharePartyID);
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
                        ArrayList<Restaurant> localRestaurants = YelpInterface.getRestaurantsFromJsonArray(response);

                        for(Restaurant r : localRestaurants) {
                            addRestaurantToParty(r);
                        }

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

    /**
     * Adds a restaurant to the DATABASE party and pushes it back to the database.  This means that it does NOT deal with the local party at all!!!
     * @param r The restaurant to be added to the party
     */
    private void addRestaurantToParty(final Restaurant r) {
        partyDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("---RETRIEVE---", "DocumentSnapshot data: " + document.getData());

                        Party tempParty = document.toObject(Party.class);
                        tempParty.addRestaurant(r);

                        db.collection("parties").document(tempParty.getPartyID()).set(tempParty);

                        restaurantsRef.document(r.getName()).set(r);
                    } else {
                        Log.d("---RETRIEVE---", "No such document");
                    }
                } else {
                    Log.d("---RETRIEVE---", "get failed with ", task.getException());
                }
            }
        });
    }

    /**
     * Clears the restaurants from the party
     */
    private void clearRestaurants() {
        restaurantsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String TAG = "---CLEAR---";
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot snapshot : task.getResult()) {
                        Restaurant temp = snapshot.toObject(Restaurant.class);
                        restaurantsRef.document(temp.getName()).delete();
                    }
                } else {
                    Log.d(TAG, "Failed to retrieve restaurant collection");
                }
            }
        });
    }
}
