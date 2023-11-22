package com.example.apcw2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apcw2.Database.DatabaseHelper;
import com.example.apcw2.R;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Bitmap imageToStorage;
    private ImageView imagePreview;
    Button savebtn, addImage, viewbtn;
    DatabaseHelper dbHelper;
    EditText nameTxt, dobTxt, emailTxt;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components and set up actions
        getImage(); // Method to handle image selection
        saveDetails(); // Method to handle saving user details
        viewDetails(); // Method to handle viewing user details
    }

    private void viewDetails(){
        viewbtn = findViewById(R.id.viewButton);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAccountDetail();
            }
        });
    }

    private void saveDetails(){
        savebtn = findViewById(R.id.savebutton);
        nameTxt = findViewById(R.id.name_et);
        dobTxt = findViewById(R.id.date_et);
        emailTxt = findViewById(R.id.email_et);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // Save account details to the database
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    dbHelper.addAccount(
                            nameTxt.getText().toString(),
                            dobTxt.getText().toString(),
                            emailTxt.getText().toString(),
                            imageToStorage
                    );
                    changeActivity();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getImage() {
        imagePreview = findViewById(R.id.imageView);
        addImage=  findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(MainActivity.this)
                        .provider(ImageProvider.BOTH)
                        .crop()
                        .maxResultSize(1080, 1080)
                        .start(101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            imagePreview.setImageURI(uri);
            try {
                imageToStorage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(MainActivity.this, "No Image", Toast.LENGTH_SHORT).show();
        }
    }


    private void moveToAccountDetail(){
        Intent i = new Intent(this, DetailActivity.class);
        startActivity(i);
    }

    private void changeActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
