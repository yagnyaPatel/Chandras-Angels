package edu.ucsb.munchease.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.data.Restaurant;
import edu.ucsb.munchease.view.RestaurantAdapter;

public class PartyHomeActivity extends AppCompatActivity {

    //The party
    private Party party;

    //Visual components of the app

    private RecyclerView recyclerView_restaurantList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseFirestore db;

    ArrayList<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_home);

        setUpFirebase();
        setUpRestaurantList();

        //------------------------------------------------------------------
        //LIST CONFIGURATION
        //------------------------------------------------------------------

        restaurants.add(new Restaurant("Restaurant 3", "0", 1, "$$", "1234 The Street", 0));
        restaurants.add(new Restaurant("Restaurant 4", "1.5", 10, "$$$$", "5678 An Avenue", 0));

        final DocumentReference docRef = db.collection("parties").document("123456");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot != null) {
                        party = documentSnapshot.toObject(Party.class);

                        String name = party.getRestaurants().get(0).getName();
                        String rating = party.getRestaurants().get(0).getRating();
                        int numberOfReviews = party.getRestaurants().get(0).getNumberOfReviews();
                        String price = party.getRestaurants().get(0).getPrice();
                        String address = party.getRestaurants().get(0).getAddress();
                        int votes = party.getRestaurants().get(0).getVotes();

                        Restaurant testRestaurant = new Restaurant(name, rating, numberOfReviews, price, address, votes);
                        restaurants.add(testRestaurant);

                        Toast.makeText(getApplicationContext(), restaurants.size() + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setUpRestaurantList() {
        recyclerView_restaurantList = findViewById(R.id.recyclerView_restaurantList);
        recyclerView_restaurantList.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView_restaurantList.setLayoutManager(layoutManager);

        //Specify an adapter
        restaurants = new ArrayList<>();
        mAdapter = new RestaurantAdapter(restaurants);
        recyclerView_restaurantList.setAdapter(mAdapter);
    }

    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }
}
