package edu.ucsb.munchease;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        button_testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference partyRef = databaseReference.child("123456");
                Map<String, Object> partyUpdates = new HashMap<>();
                partyUpdates.put("members", 5);
                partyRef.updateChildren(partyUpdates);
            }
        });

        databaseReference.child("123456").child("members").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Party party = dataSnapshot.getValue(Party.class);
                //textView_testNumber.setText(party.getMembers() + "");

                Integer members = dataSnapshot.getValue(Integer.class);
                textView_testNumber.setText(members + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
