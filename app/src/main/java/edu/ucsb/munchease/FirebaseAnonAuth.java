package edu.ucsb.munchease;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class FirebaseAnonAuth {
    private static FirebaseAuth fbAuth;

    // TODO: May not need at all
    public static void signIn() {
        /*fbAuth = FirebaseAuth.getInstance();

        boolean result;

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // TODO: Handle any actions required for success/failure
                        if(task.isSuccessful()) { }
                        else { }
                    }

                });*/
    }
}
