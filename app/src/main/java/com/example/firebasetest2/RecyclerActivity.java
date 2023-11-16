package com.example.firebasetest2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

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

        recyclerView = findViewById(R.id.recyclerView); // 리사이클러뷰 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // LostAnimal 객체 담을 Arraylist

        database = FirebaseDatabase.getInstance(); // Firebase Database Connect

        databaseReference = database.getReference("LostAnimal"); //DB Table Connect
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Firebase Database 데이터를 받아오는 곳
                arrayList.clear(); // ArrayList init
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List 추출
                    LostAnimal lostAnimal = snapshot.getValue(LostAnimal.class); // LostAnimal 객체에 데이터 전송
                    arrayList.add(lostAnimal); // 전송된 데이터 arraylist에 넣어 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // DB error
                Log.e("RecyclerActivity", String.valueOf(databaseError.toException())); // print error
            }
        });

        adapter = new LostAnimalAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // Recyclerview adapter connect
    }

}
