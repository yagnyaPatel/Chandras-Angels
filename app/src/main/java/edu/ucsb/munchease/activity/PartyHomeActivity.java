package edu.ucsb.munchease.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.view.RestaurantAdapter;

public class PartyHomeActivity extends AppCompatActivity {

    //Visual components of the app
    private TextView textView_testNumber;
    private Button button_testButton;

    private RecyclerView recyclerView_restaurantList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Database instance
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //Database reference points
    private DatabaseReference databaseRef = database.getReference();
    private DatabaseReference partyRef = databaseRef.child("123456/party");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_home);

        //Visual components of the app
        textView_testNumber = findViewById(R.id.textView_testNumber);
        button_testButton = findViewById(R.id.button_testButton);

        recyclerView_restaurantList = findViewById(R.id.recyclerView_restaurantList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView_restaurantList.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView_restaurantList.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = {"Restaurant 1", "Restaurant 2"};
        mAdapter = new RestaurantAdapter(myDataset);
        recyclerView_restaurantList.setAdapter(mAdapter);

        //Database instance
        database = FirebaseDatabase.getInstance();

        //Database reference points
        databaseRef = database.getReference();
        partyRef = databaseRef.child("123456/party");

        //Increment the value of "members" in the database when the button is clicked
        button_testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                   //Whenever the button is clicked
                partyRef.child("members").addListenerForSingleValueEvent(new ValueEventListener() {     //Get a snapshot of the current data at "123456/party/members"
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer members = dataSnapshot.getValue(Integer.class);                         //Get the value of the data
                        Map<String, Object> partyUpdates = new HashMap<>();
                        partyUpdates.put("members", members + 1);                                       //Increment the value of "members"
                        partyRef.updateChildren(partyUpdates);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //Update the text when the "members" field changes in the database
        databaseRef.child("123456/party/members").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textView_testNumber.setText(dataSnapshot.getValue() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Update the text when the "members" field changes in the database
        //This uses a different method from above - will look for any changes to children of the party in the database
        //If uncommented at the same time as above code block, will break the app
        /*databaseRef.child("123456/party").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                textView_testNumber.setText(dataSnapshot.getValue() + "");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
