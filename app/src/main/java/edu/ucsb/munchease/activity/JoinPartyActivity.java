package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.ucsb.munchease.R;
import edu.ucsb.munchease.data.MunchEaseValues;

public class JoinPartyActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private DocumentReference partyDocRef;

    private EditText editText_joinPartyID;
    private Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_party);

        setUpEditText();
        setUpButtonNext();

        setUpFirebase();
    }

    /**
     * Initializes the Firebase references
     */
    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    private void setUpButtonNext() {
        button_next = findViewById(R.id.button_next);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partyID = editText_joinPartyID.getText().subSequence(0,3) + "-" + editText_joinPartyID.getText().subSequence(3,6);
                try {
                    joinParty(partyID);
                } catch(IllegalArgumentException e) {
                    Log.d("joinParty", e.getMessage());
                }

            }
        });
    }

    private void setUpEditText() {
        editText_joinPartyID = findViewById(R.id.editText_joinPartyID);

        editText_joinPartyID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() == MunchEaseValues.PARTY_ID_LENGTH) {
                    button_next.setEnabled(true);
                } else {
                    button_next.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void joinParty(final String partyID)  {
        db.collection("parties").document(partyID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("---RETRIEVE---", "Party already exists: " + document.getData());
                        Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                        goToPartyHomeActivity.putExtra("sharePartyID", partyID);
                        startActivity(goToPartyHomeActivity);

                    } else {
                        Log.d("---RETRIEVE---", "No such document - creating now");
                    }
                } else {
                    Log.d("---RETRIEVE---", "get failed with ", task.getException());
                }
            }
        });
    }
}
