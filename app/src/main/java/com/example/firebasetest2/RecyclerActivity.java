package com.example.firebasetest2;

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
        setContentView(R.layout.activity_recyclerview);

        recyclerView = findViewById(R.id.recyclerView); // 아이디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // LostAnimal 객체 담을 Arraylist

        database = FirebaseDatabase.getInstance(); // Firebase Database Connection
        databaseReference = database.getReference("LostAnimal"); // DB table connect
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Firebase Database 데이터 받는함수
                arrayList.clear(); //기존 ArrayList 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 List 추출
                    LostAnimal lostAnimal = snapshot.getValue(LostAnimal.class); // 만들어뒀던 LostAnimal 객체에 데이터 담기
                    arrayList.add(lostAnimal); // 담은 데이터들을 Arraylist에 넣고 RecyclerView로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error 발생 시
                Log.e("RecyclerActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        adapter = new LostAnimalAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
}
