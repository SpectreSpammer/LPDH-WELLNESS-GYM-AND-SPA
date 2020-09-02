package com.example.lpdhwellnessgymandspa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountDetailsActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    TextView fullName,homeAdd,email,phone;

    String mName,mEmail,mHomeAdd,mPhone;
    Toolbar mtoolbar;

    Button btnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        mtoolbar = findViewById(R.id.aToolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Profile");



        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.txt_Fullname);
        homeAdd = findViewById(R.id.txt_address);
        email = findViewById(R.id.txt_emailadd);
        phone = findViewById(R.id.txt_contactNum);

        btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkUserprofile();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }

            private void checkUserprofile() {
                DocumentReference docRef = firestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
            }
        });

        DocumentReference docRef =firestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    mName = documentSnapshot.getString("firstName") + " " + documentSnapshot.getString("lastName");
                    mHomeAdd = documentSnapshot.getString("Address");
                    mEmail = documentSnapshot.getString("email");
                    mPhone = firebaseAuth.getCurrentUser().getPhoneNumber();

                    fullName.setText(mName);
                    homeAdd.setText(mHomeAdd);
                    email.setText(mEmail);
                    phone.setText(mPhone);
                }else {
                    Log.d(TAG, "Retrieving Data: Profile Data Not Found ");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity   .class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
