package com.example.meong_nyang;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LostAnimalDetailActivity extends AppCompatActivity {
    Intent intent;
    String title;
    String content;
    String lostlocation;
    String lostdate;
    String losttime;
    String name;
    String callnum;
    String species;
    String image1;
    String image2;
    String image3;

    TextView title_tv, content_tv, lostlocation_tv, lostdate_tv, losttime_tv, name_tv, callnum_tv, species_tv;
    ImageView imageView1, imageView2, imageView3;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostanimal_detail);

        //add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //액티비티의 앱바(App Bar)로 지정
        getSupportActionBar().setDisplayShowTitleEnabled(false); //앱바의 App Name 비활성화
        ActionBar actionBar = getSupportActionBar(); //앱바 제어를 위해 툴바 액세스
        actionBar.setDisplayHomeAsUpEnabled(true); // 앱바에 뒤로가기 버튼 만들기

        title_tv = findViewById(R.id.title);
        content_tv = findViewById(R.id.content);
        lostlocation_tv = findViewById(R.id.lostlocation);
        lostdate_tv = findViewById(R.id.lostdate);
        losttime_tv = findViewById(R.id.losttime);
        name_tv = findViewById(R.id.name);
        species_tv = findViewById(R.id.species);
        callnum_tv = findViewById(R.id.callnum);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);


        intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        lostlocation = intent.getStringExtra("lostlocation");
        lostdate = intent.getStringExtra("lostdate");
        losttime = intent.getStringExtra("losttime");
        name = intent.getStringExtra("name");
        species = intent.getStringExtra("species");
        callnum = intent.getStringExtra("callnum");
        image1 = intent.getStringExtra("image1");
        image2 = intent.getStringExtra("image2");
        image3 = intent.getStringExtra("image3");

        title_tv.setText(title);
        content_tv.setText(content);
        lostlocation_tv.setText("실종된 장소 : " + lostlocation);
        lostdate_tv.setText("실종된 날짜 : " + lostdate);
        losttime_tv.setText("실종된 시간 : " + losttime);
        name_tv.setText("반려동물 이름 : " + name);
        species_tv.setText("견종 및 묘종 : " + species);
        callnum_tv.setText("보호자 연락처 : " + callnum);

        uri = Uri.parse(image1);
        Glide.with(LostAnimalDetailActivity.this).load(uri).into(imageView1);

        // Load and display image2
        if (image2 != null && !image2.isEmpty()) {
            uri = Uri.parse(image2);
            Glide.with(LostAnimalDetailActivity.this).load(uri).into(imageView2);
        } else {
            // If image2 is null or empty, you can hide or set a placeholder for imageView2
            imageView2.setVisibility(View.GONE);
        }

        // Load and display image3
        if (image3 != null && !image3.isEmpty()) {
            uri = Uri.parse(image3);
            Glide.with(LostAnimalDetailActivity.this).load(uri).into(imageView3);
        } else {
            // If image3 is null or empty, you can hide or set a placeholder for imageView3
            imageView3.setVisibility(View.GONE);
        }
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
}
