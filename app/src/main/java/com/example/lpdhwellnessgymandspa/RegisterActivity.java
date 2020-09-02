package com.example.lpdhwellnessgymandspa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    EditText firstName, lastName , homeAdd  , emailAdd;
    Button btnSave;
    String userID;
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mtoolbar = findViewById(R.id.aToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Register");

        firstName = findViewById(R.id.txt_FirstName);
        lastName = findViewById(R.id.txt_LastName);
        homeAdd = findViewById(R.id.txt_address);
        emailAdd = findViewById(R.id.txt_emailadd);
        btnSave = findViewById(R.id.btn_saved);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        final DocumentReference docRef = firestore.collection("users").document(userID);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() &&
                        !homeAdd.getText().toString().isEmpty()
                        && !emailAdd.getText().toString().isEmpty())
                {
                    String first = firstName.getText().toString();
                    String last = lastName.getText().toString();
                    String Address = homeAdd.getText().toString();
                    String userEmail = emailAdd.getText().toString();

                    Map<String,Object> user = new HashMap<>();
                    user.put("firstName",first);
                    user.put("lastName",last);
                    user.put("Address",Address);
                    user.put("email",userEmail);

                    docRef.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), AccountDetailsActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                            }

            }
        });


    }
                else
                {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
}
        });
    }
}
