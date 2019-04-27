package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.ucsb.munchease.data.Party;
import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Restaurant;

public class SharePartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_party);

        Party party = new Party();
        party.addRestaurant(new Restaurant("Restaurant 1", 5.0, "1234 The Street"));
        party.addRestaurant(new Restaurant("Restaurant 2", 3.0, "5678 An Avenue"));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(party.getPartyID() + "/party");
        myRef.setValue(party);

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
