package edu.ucsb.munchease.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Random;

import javax.annotation.Nullable;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.view.RestaurantAdapter;

public class PartyHomeActivity extends AppCompatActivity {

    //------------------------------------------------------------------
    // *** INSTANCE VARIABLES ***
    //------------------------------------------------------------------

    //The party
    private Party party;

    //Visual components of the app

    private Button button_addRandomRestaurant;
    private Button button_clearRestaurants;

    private RecyclerView recyclerView_restaurantList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore db;
    private DocumentReference partyDocRef;
    private DocumentReference testRef;
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

        party = new Party();
        //party.addRestaurant(new Restaurant("Local Restaurant 1", "5", 25, "$$", "1234 The Street")); //Test restaurant

        setUpFirebase();
        populateDatabase();
        setUpRestaurantList();
        //retrievePartyFromDatabase(); //Apparently do not actually need this with the listener set up, but might change
        setUpDatabaseListener();

        button_addRandomRestaurant = findViewById(R.id.button_addRandomRestaurant);
        button_addRandomRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRestaurantToParty(generateRandomRestaurant());
            }
        });

        button_clearRestaurants = findViewById(R.id.button_clearRestaurants);
        button_clearRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                party.clearRestaurants();
                partyDocRef.set(party);
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
        mAdapter = new RestaurantAdapter(party.getRestaurants());
        recyclerView_restaurantList.setAdapter(mAdapter);
    }

    /**
     * Initializes the Firebase references
     */
    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
        partyDocRef = db.collection("parties").document("123456");
        testRef = db.collection("parties").document("123456").collection("restaurants").document("epic");

        Restaurant testRestaurant  = new Restaurant("Subcollection Restaurant 1", "5", 25, "$$", "1234 The Street");
        testRef.set(testRestaurant);

        restaurantsRef = partyDocRef.collection("restaurants");
    }

    /**
     * Adds dummy data to the database
     */
    private void populateDatabase() {
        Party party2 = new Party();
        party2.addRestaurant(new Restaurant("DB Restaurant 1", "5", 25, "$$", "1234 The Street"));
        party2.addRestaurant(new Restaurant("DB Restaurant 2", "3", 50, "$$$$", "5678 An Avenue"));

        // Add a new document with a generated ID
        db.collection("parties").document(party2.getPartyID()).set(party2);
    }

    /**
     * Gets the latest information from the database and updates the RecyclerView adapter to reflect any changes
     * Currently does not properly support updating on data changes, and will just add to the end of the list instead of updating items
     */
    private void retrievePartyFromDatabase() {
        partyDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("---RETRIEVE---", "DocumentSnapshot data: " + document.getData());

                        Party tempParty = document.toObject(Party.class);
                        int startPosition = party.getRestaurants().size();

                        for(int i = startPosition; i < tempParty.getRestaurants().size(); i++) {
                            party.addRestaurant(tempParty.getRestaurants().get(i));
                        }

                        mAdapter.notifyItemRangeInserted(startPosition, party.getRestaurants().size());
                    } else {
                        Log.d("---RETRIEVE---", "No such document");
                    }
                } else {
                    Log.d("---RETRIEVE---", "get failed with ", task.getException());
                }
            }
        });
    }

    private void setUpDatabaseListener() {
        partyDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String TAG = "---LISTENER---";

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d(TAG, "Current data: " + documentSnapshot.getData());

                    Party tempParty = documentSnapshot.toObject(Party.class);

                    party.clearRestaurants();

                    int startPosition = party.getRestaurants().size();

                    for(int i = startPosition; i < tempParty.getRestaurants().size(); i++) {
                        party.addRestaurant(tempParty.getRestaurants().get(i));
                    }

                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    /**
     * Generates a random restaurant
     * @return a random restaurant
     */
    private Restaurant generateRandomRestaurant() {
        Random random = new Random();

        String restaurantName = "DB Restaurant " + random.nextInt(100);
        String rating = random.nextInt(5) + 1 + "";
        int numberOfReviews = random.nextInt(500);

        String price = "";
        int priceNum = random.nextInt(3) + 1;
        for(int i = 0; i < priceNum; i++) {
            price += "$";
        }

        int addressNum = random.nextInt(10000);
        String address = addressNum + " The Street";

        Restaurant r = new Restaurant(restaurantName, rating, numberOfReviews, price, address);
        return r;
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
                    } else {
                        Log.d("---RETRIEVE---", "No such document");
                    }
                } else {
                    Log.d("---RETRIEVE---", "get failed with ", task.getException());
                }
            }
        });
    }
}
