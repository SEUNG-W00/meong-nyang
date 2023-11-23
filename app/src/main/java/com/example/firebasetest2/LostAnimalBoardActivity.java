package com.example.firebasetest2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LostAnimalBoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<LostAnimal> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostanimal_board);

        //add toolbar
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar); //액티비티의 앱바(App Bar)로 지정
        getSupportActionBar().setDisplayShowTitleEnabled(false); //앱바의 App Name 비활성화
        ActionBar actionBar = getSupportActionBar(); //앱바 제어를 위해 툴바 액세스

        recyclerView = findViewById(R.id.recyclerView); // 리사이클러뷰 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // LostAnimal 객체 담을 Arraylist

        database = FirebaseDatabase.getInstance(); // Firebase Database Connect

        databaseReference = database.getReference("LostAnimal"); // DB Table Connect
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Firebase Database 데이터를 받아오는 곳
                arrayList.clear(); // ArrayList init
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) { // 반복문으로 데이터 List 추출
                    LostAnimal lostAnimal = dataSnapshot1.getValue(LostAnimal.class); // LostAnimal 객체에 데이터 전송
                    arrayList.add(lostAnimal); // 전송된 데이터 arraylist에 넣어 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // DB error
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show(); // print error
                Log.e("RecyclerActivity", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new LostAnimalAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // Recyclerview adapter connect

    }

    // Inflating the menu items from the menu_items.xml file


    // Handling the click events of the menu items
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Switching on the item id of the menu item
        if(item.getItemId() == R.id.registerbtn) {
            Intent intent = new Intent(getApplicationContext(), LostAnimalRegisterActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(getApplicationContext(), QnAActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
