package edu.ucsb.munchease;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PartyHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_home);

        final TextView textView_testNumber = findViewById(R.id.textView_testNumber);
        final Button button_testButton = findViewById(R.id.button_testButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();
        final DatabaseReference partyRef = databaseReference.child("123456/party");

        //Increment the value of "members" in the database when the button is clicked
        button_testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partyRef.child("members").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer members = dataSnapshot.getValue(Integer.class);
                        Map<String, Object> partyUpdates = new HashMap<>();
                        partyUpdates.put("members", members + 1);
                        partyRef.updateChildren(partyUpdates);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //User a different method to update the text.  If this is uncommented at the same time as the second method (below) it will break the code
        /*databaseReference.child("123456").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Party theParty = dataSnapshot.getValue(Party.class);
                textView_testNumber.setText(theParty.getMembers() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //Update the text when the "members" field changes in the database
        databaseReference.child("123456/party").addChildEventListener(new ChildEventListener() {
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
        });
    }
}
