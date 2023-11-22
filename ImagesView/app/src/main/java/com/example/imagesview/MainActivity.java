package com.example.imagesview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnPrevious;
    private Button btnNext;
    private int[] imageIds = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnPrevious = findViewById(R.id.prevButton);
        btnNext = findViewById(R.id.nextButton);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    imageView.setImageResource(imageIds[currentIndex]);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < imageIds.length - 1) {
                    currentIndex++;
                    imageView.setImageResource(imageIds[currentIndex]);
                }
            }
        });
    }
}

