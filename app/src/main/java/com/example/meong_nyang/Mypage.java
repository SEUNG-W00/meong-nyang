package com.example.meong_nyang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mypage extends AppCompatActivity {
  private FirebaseAuth mFirebaseAuth;
  private DatabaseReference mDatabaseRef;
  private Button btn_logout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mypage);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar); //액티비티의 앱바(App Bar)로 지정
    getSupportActionBar().setDisplayShowTitleEnabled(false); //앱바의 App Name 비활성화

    mFirebaseAuth = FirebaseAuth.getInstance();
    mDatabaseRef = FirebaseDatabase.getInstance().getReference("straydog");

    btn_logout = findViewById(R.id.logout);
    btn_logout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //로그아웃 하기
        mFirebaseAuth.signOut();
        Intent intent = new Intent(Mypage.this, Login.class);
        startActivity(intent);
        finish();
      }
    });

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

    //bottomNavigation 전환
    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @SuppressLint("NonConstantResourceId")
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //if문으로 item.getItemId()값이 메뉴의 id값과 같은지 확인후 intent 로 activity로 전환
        if (item.getItemId() == R.id.info) {
          Intent intent = new Intent(Mypage.this, MainActivity2.class);
          startActivity(intent);
        } else if (item.getItemId() == R.id.missing) {
          Intent intent = new Intent(Mypage.this, LostAnimalBoardActivity.class);
          startActivity(intent);
        } else if (item.getItemId() == R.id.question) {
          Intent intent = new Intent(Mypage.this, QnAActivity.class);
          startActivity(intent);
        } else if (item.getItemId() == R.id.apply) {
          Intent intent = new Intent(Mypage.this, Volunteers.class);
          startActivity(intent);
        } else if (item.getItemId() == R.id.mypage) {
          Intent intent = new Intent(Mypage.this, Mypage.class);
          startActivity(intent);
        }
        return true; // return true;
      }
    });

    //탈퇴 처리

  }


}