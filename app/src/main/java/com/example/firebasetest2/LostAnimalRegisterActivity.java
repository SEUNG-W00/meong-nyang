package com.example.firebasetest2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;

public class LostAnimalRegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Uri[] uris = new Uri[3];
    private ImageButton selectImage;
    private ImageView[] imageViews = new ImageView[3];
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

        selectImage = findViewById(R.id.selectimage);

        imageViews[0] = findViewById(R.id.imageView1);
        imageViews[1] = findViewById(R.id.imageView2);
        imageViews[2] = findViewById(R.id.imageView3);

        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        lostlocation = findViewById(R.id.lostlocation_et);
        lostdate = findViewById(R.id.lostdate_et);
        losttime = findViewById(R.id.losttime_et);
        name = findViewById(R.id.name_et);
        species = findViewById(R.id.species_et);
        callnum = findViewById(R.id.callnum_et);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
    }

    private void select() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT );
        intent.setType("image/*");
        launcher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();

                    // Determine the ImageView to set the image
                    ImageView targetImageView = determineTargetImageView();

                    // Set the image to the determined ImageView
                    if (targetImageView != null) {
                        int index = Arrays.asList(imageViews).indexOf(targetImageView);
                        uris[index] = uri;
                        targetImageView.setImageURI(uri);
                        Log.d("test", uri.toString());
                    } else {
                        // Handle the case where all ImageViews are occupied
                        Toast.makeText(LostAnimalRegisterActivity.this, "You can only select up to 3 images", Toast.LENGTH_SHORT).show();
                    }

                }
            });

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

        if (item.getItemId() == R.id.registercompletebtn) {
            // Handle the "Register" menu item click
            upload();
        }
        else if(item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), LostAnimalBoardActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ImageView determineTargetImageView() {
        for (ImageView imageView : imageViews) {
            if (imageView.getDrawable() == null) {
                return imageView;
            }
        }
        return null;
    }

    private void upload() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("meong-nyang");

        for (int i = 0; i < 3; i++) {
            if (imageViews[i].getDrawable() != null && uris[i] != null) {
                final int index = i;
                storageReference.child("images/image" + (index + 1)).putFile(uris[i]).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            // Get the download URL of the uploaded image
                            Toast.makeText(LostAnimalRegisterActivity.this, "Image" + (index + 1) + " upload success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LostAnimalRegisterActivity.this, "Image " + (index + 1) + " upload failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}