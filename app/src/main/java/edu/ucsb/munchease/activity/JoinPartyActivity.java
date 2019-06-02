package edu.ucsb.munchease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                String partyID = editText_joinPartyID.getText().toString();
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

                if (count == 1) {
                    // auto insert dashes while user typing their ssn
                    if (start == 2) {
                        editText_joinPartyID.setText(editText_joinPartyID.getText() + "-");
                        editText_joinPartyID.setSelection(editText_joinPartyID.getText().length());
                    }
                }

                if(s.toString().length() == MunchEaseValues.PARTY_ID_LENGTH + 1) {
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
                        Intent goToPartyHomeActivity = new Intent(getApplicationContext(), PartyHomeActivity.class);
                        goToPartyHomeActivity.putExtra("sharePartyID", partyID);
                        startActivity(goToPartyHomeActivity);

                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Party does not exist", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 32);
                        toast.show();
                    }
                } else {
                    Log.d("---RETRIEVE---", "get failed with ", task.getException());
                }
            }
        });
    }
}
