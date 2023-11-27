package com.example.firebasetest2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostAnimalRegisterActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText lostlocation, losttime, lostdate, name, species, callnum, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostanimalregister);

        //add toolbar
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar); //액티비티의 앱바(App Bar)로 지정
        getSupportActionBar().setDisplayShowTitleEnabled(false); //앱바의 App Name 비활성화
        ActionBar actionBar = getSupportActionBar(); //앱바 제어를 위해 툴바 액세스
        actionBar.setDisplayHomeAsUpEnabled (true); //앱바에 뒤로가기 버튼 만들기

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

    // CustomToolBar 반영하기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate (R.menu.lostanimalregister_menu_top, menu);
        return true;
    }

    // Toolbar back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.registercompletebtn) {
            // Handle the "Register" menu item click
        }

        switch (item.getItemId()) {
            case android.R.id.home: { //툴바 뒤로가기버튼 눌렸을 때 동작

                Intent intent = new Intent(getApplicationContext(), LostAnimalBoardActivity.class);
                startActivity(intent);

                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendData(View view) {
    }

}
