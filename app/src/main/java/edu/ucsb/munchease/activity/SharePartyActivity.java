package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;

public class SharePartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_party);

        Party party = new Party();
        party.addRestaurant(new Restaurant("Restaurant 1", "5", 25, "$$", "1234 The Street"));
        party.addRestaurant(new Restaurant("Restaurant 2", "3", 50, "$$$$", "5678 An Avenue"));

        /*final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(party.getPartyID() + "/party");
        myRef.setValue(party);*/

        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Add a new document with a generated ID
        db.collection("parties").document(party.getPartyID()).set(party);

        //Make "NEXT" button (button_next) take you to the Party Home Activity (PartyHomeActivity)
        Button button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                startActivity(goToPartyHomeActivity);
            }
        });
    }
}
