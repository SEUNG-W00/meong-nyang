package com.example.firebasetest2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasetest2.databinding.ActivityLostanimalregisterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostAnimalRegisterActivity extends AppCompatActivity {

    ActivityLostanimalregisterBinding binding;
    String title, content, lostlocation, lostdate, losttime, image;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLostanimalregisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}
