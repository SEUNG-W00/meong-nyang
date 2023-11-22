package com.example.firebasetest2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostAnimalRegister extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText lostlocation, losttime, lostdate, name, species, callnum, title, content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostanimalregister);

        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        lostlocation = findViewById(R.id.lostlocation_et);
        lostdate = findViewById(R.id.lostdate_et);
        losttime = findViewById(R.id.losttime_et);
        name = findViewById(R.id.name_et);
        species = findViewById(R.id.species_et);
        callnum = findViewById(R.id.callnum_et);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void sendData(View view) {
        writeNewLostAnimal();
    }

    public void writeNewLostAnimal() {
        LostAnimal lostAnimal = new LostAnimal();

    }
}
