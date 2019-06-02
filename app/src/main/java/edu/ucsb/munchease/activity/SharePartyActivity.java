package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.Party;

public class SharePartyActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private DocumentReference partyDocRef;

    private Party party;

    private Button button_next;
    private TextView textView_sharePartyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_party);

        initParty();
        setUpFirebase();

        //Make "NEXT" button (button_next) take you to the Party Home Activity (PartyHomeActivity)
        setUpButtonNext();
    }

    /**
     * Initializes the Firebase references
     */
    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
        partyDocRef = db.collection("parties").document(party.getPartyID());
        partyDocRef.set(party);
    }

    private void initParty() {
        String partyID = generatePartyID();
        party = new Party(partyID);

        textView_sharePartyCode = findViewById(R.id.textView_sharePartyCode);
        textView_sharePartyCode.setText(partyID);
    }

    private void setUpButtonNext() {
        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                goToPartyHomeActivity.putExtra("sharePartyID", textView_sharePartyCode.getText());
                startActivity(goToPartyHomeActivity);
            }
        });
    }

    private String generatePartyID() {

        Random random = new Random();
        int firstHalf = random.nextInt(899) + 100; //Generate random numbers between 100 and 999
        int secondHalf = random.nextInt(899) + 100;

        return firstHalf + "-" + secondHalf;
    }
}
