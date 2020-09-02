package com.example.lpdhwellnessgymandspa;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountActivityFragment extends AppCompatActivity {

    Button btnLogout;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_account);

        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        btnLogout = findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               FirebaseAuth.getInstance().signOut();
               finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
